
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Administrator;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed repository -------------------------------------------
	@Autowired
	private MessageRepository	messageRepository;

	// Supported services -------------------------------------------
	@Autowired
	private ActorService		actorService;

	@Autowired
	private FolderService		folderService;


	// Constructor methods -------------------------------------------
	public MessageService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Message create() {
		Message result;
		Actor principal;

		result = new Message();

		// Principal must be an actor
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		result.setSender(principal);

		final Date moment = new Date(System.currentTimeMillis() - 1000);
		result.setMoment(moment);

		result.setTags(new ArrayList<String>());

		return result;
	}

	public Message findOne(final int id) {
		final Message result = this.messageRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Message> findAll() {
		final Collection<Message> result = this.messageRepository.findAll();

		return result;
	}

	public Message save(final Message message) {
		Assert.notNull(message);

		message.setFolder(this.folderService.findOutboxByPrincipal(message.getSender().getId()));

		this.checkMessage(message);

		// Creamos la copia para el outbox del receiver
		final Message copy = this.createCopyForInbox(message);
		this.messageRepository.save(copy);

		return this.messageRepository.save(message);
	}
	public void delete(final int messageId) {

		Actor principal;

		final Message m = this.findOne(messageId);

		Assert.notNull(m);

		// Principal must be an actor
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		// Message must belong to any folder of the principal
		final Collection<Folder> folders = this.folderService.findAllFoldersByActor(principal.getId());
		Assert.isTrue(folders.contains(m.getFolder()));

		// Si está marcado como DELETED, se borra, de lo contrario se añade el tag "DELETED"
		if (m.getTags().contains("DELETED"))
			this.messageRepository.delete(m);
		else {
			m.getTags().add("DELETED");
			this.messageRepository.save(m);
		}

	}
	// Other business methods ---------------------------------------------------------

	public void flush() {
		this.messageRepository.flush();
	}

	public void checkMessage(final Message message) {
		boolean check = true;

		//Hacer comprobaciones
		if (message.getReceiver() == null || message.getFolder() == null || message.getBody() == null || message.getSubject() == null || message.getTags() == null)
			check = false;

		if (message.getMoment() == null || message.getMoment().after(new Date()))
			check = false;

		Assert.isTrue(check);
	}

	public Message createCopyForInbox(final Message m) {
		final Message result = new Message();

		result.setSubject(m.getSubject());
		result.setBody(m.getBody());
		result.setMoment(m.getMoment());
		result.setTags(m.getTags());

		result.setSender(m.getSender());
		result.setReceiver(m.getReceiver());

		final Folder inbox = this.folderService.findFolderByActor(m.getReceiver().getId(), "Inbox");

		result.setFolder(inbox);

		return result;
	}

	public Collection<Message> findAllMessagesByFolder(final int folderId) {
		// Principal must be an actor
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		return this.messageRepository.findAllMessagesByFolder(folderId);
	}

	public List<String> findAllTagsByPrincipal(final int id) {
		final Set<String> result = new HashSet<String>();

		final Collection<Message> inbox = this.findAllInboxMessagesByPrincipal(id);
		final Collection<Message> outbox = this.findAllOutboxMessagesByPrincipal(id);

		if (inbox.isEmpty() == false)
			for (final Message m : inbox)
				result.addAll(m.getTags());

		if (outbox.isEmpty() == false)
			for (final Message m : outbox)
				result.addAll(m.getTags());

		final List<String> res = new ArrayList<String>();
		res.addAll(result);

		return res;
	}

	public Collection<Message> findAllInboxMessagesByPrincipal(final int id) {
		Collection<Message> result = new ArrayList<Message>();

		final Folder inbox = this.folderService.findInboxByPrincipal(id);

		result = this.findAllMessagesByFolder(inbox.getId());

		return result;
	}

	public Collection<Message> findAllOutboxMessagesByPrincipal(final int id) {
		Collection<Message> result = new ArrayList<Message>();
		// Principal must be an actor

		final Folder outbox = this.folderService.findOutboxByPrincipal(id);

		result = this.findAllMessagesByFolder(outbox.getId());

		return result;
	}

	public Collection<Message> findAllInboxMessagesByTag(final String tag, final int actorId) {
		Collection<Message> result = new ArrayList<Message>();

		// Principal must be an actor
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		final Folder inbox = this.folderService.findInboxByPrincipal(actorId);

		result = this.messageRepository.findAllMessagesByFolderAndTag(inbox.getId(), tag);

		return result;
	}

	public Collection<Message> findAllOutboxMessagesByTag(final String tag, final int actorId) {
		Collection<Message> result = new ArrayList<Message>();

		// Principal must be an actor
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		final Folder outbox = this.folderService.findOutboxByPrincipal(actorId);

		result = this.messageRepository.findAllMessagesByFolderAndTag(outbox.getId(), tag);

		return result;
	}

	public void broadcast(final Message m) {
		// Principal must be an admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Assert.isTrue(m.getSender().equals(principal));

		final Collection<Actor> receivers = this.actorService.findAll();
		receivers.remove(principal);

		m.getTags().add("SYSTEM");

		for (final Actor a : receivers) {
			m.setReceiver(a);
			this.save(m);
		}
	}

	public Message findOneToEdit(final int id) {
		Message m;

		final Actor principal = this.actorService.findByPrincipal();

		m = this.findOne(id);

		// Message must belong to any folder of the principal
		final Collection<Folder> folders = this.folderService.findAllFoldersByActor(principal.getId());
		Assert.isTrue(folders.contains(m.getFolder()));

		return m;
	}

}
