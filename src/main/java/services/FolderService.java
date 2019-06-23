
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;

@Service
@Transactional
public class FolderService {

	// Managed repository -------------------------------------------
	@Autowired
	private FolderRepository	folderRepository;

	// Supported services -------------------------------------------
	@Autowired
	private ActorService		actorService;


	// Constructor methods -------------------------------------------
	public FolderService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Folder findOne(final int id) {
		final Folder result = this.folderRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Folder> findAll() {
		final Collection<Folder> result = this.folderRepository.findAll();

		return result;
	}

	public Folder save(final Folder folder) {
		Assert.notNull(folder);

		Assert.notNull(folder.getName());
		Assert.notNull(folder.getOwner());

		return this.folderRepository.save(folder);
	}

	// Other business methods ---------------------------------------------------------

	public void flush() {
		this.folderRepository.flush();
	}

	public Collection<Folder> findAllFoldersByActor(final int actorId) {
		// Principal must be an actor
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		return this.folderRepository.findAllFoldersByActor(actorId);
	}

	public void generateFolders(final int actorId) {
		final Actor owner = this.actorService.findOne(actorId);

		Assert.notNull(actorId);

		final Folder inbox = new Folder();
		inbox.setName("Inbox");
		inbox.setOwner(owner);

		final Folder outbox = new Folder();
		outbox.setName("Outbox");
		outbox.setOwner(owner);

		this.save(inbox);
		this.save(outbox);
	}

	public Folder findInboxByPrincipal(final int id) {
		// Id must not be null
		Assert.isTrue(id != 0);

		return this.folderRepository.findInboxByPrincipal(id);
	}
	public Folder findOutboxByPrincipal(final int id) {
		// Id must not be null
		Assert.isTrue(id != 0);

		return this.folderRepository.findOutboxByPrincipal(id);
	}

	public Folder findFolderByActor(final int actorId, final String name) {
		return this.folderRepository.findFolderByActor(actorId, name);
	}

}
