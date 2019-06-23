
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import domain.Actor;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService	messageService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String searcher) {

		ModelAndView res;
		Collection<Message> inbox = new ArrayList<Message>();
		Collection<Message> outbox = new ArrayList<Message>();
		//final Collection<String> tags = new ArrayList<String>();
		String requestURI = "message/list.do";
		Actor principal;

		principal = this.actorService.findByPrincipal();

		if (searcher != null) {
			inbox = this.messageService.findAllInboxMessagesByTag(searcher, principal.getId());
			outbox = this.messageService.findAllOutboxMessagesByTag(searcher, principal.getId());
			requestURI += "?searcher" + searcher;
		} else {
			inbox = this.messageService.findAllInboxMessagesByPrincipal(principal.getId());
			outbox = this.messageService.findAllOutboxMessagesByPrincipal(principal.getId());
		}

		//tags.addAll(this.messageService.findAllTagsByPrincipal(principal.getId()));

		res = new ModelAndView("message/list");
		res.addObject("inbox", inbox);
		res.addObject("outbox", outbox);
		//res.addObject("tags", tags);
		res.addObject("requestURI", requestURI);

		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Message m;
		Actor principal;

		principal = this.actorService.findByPrincipal();

		m = this.messageService.create();
		m.setSender(principal);
		m.setFolder(this.folderService.findOutboxByPrincipal(principal.getId()));

		res = this.createModelAndView(m);

		return res;
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {
		ModelAndView res;
		Message m;

		m = this.messageService.create();
		res = this.broadcastModelAndView(m);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int messageId) {
		ModelAndView result;
		Message m;
		m = this.messageService.findOne(messageId);

		result = new ModelAndView("message/show");
		result.addObject("mess", m);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId) {

		ModelAndView res;

		this.messageService.delete(messageId);

		res = new ModelAndView("redirect:/message/list.do");
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message m, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createModelAndView(m);
		} else
			try {
				this.messageService.save(m);
				res = new ModelAndView("redirect:/message/list.do");
			} catch (final Throwable oops) {
				res = this.createModelAndView(m, "message.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "save")
	public ModelAndView saveBroadcast(final Message m, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.broadcastModelAndView(m);
		} else
			try {
				this.messageService.broadcast(m);
				res = new ModelAndView("redirect:/message/list.do");
			} catch (final Throwable oops) {
				res = this.broadcastModelAndView(m, "message.commit.error");
			}
		return res;
	}

	protected ModelAndView createModelAndView(final Message m) {
		ModelAndView res;

		res = this.createModelAndView(m, null);

		return res;
	}

	protected ModelAndView createModelAndView(final Message m, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("message/create");

		final Collection<Actor> actors = this.actorService.findAll();
		actors.remove(this.actorService.findByPrincipal());

		res.addObject("mess", m);
		res.addObject("actors", actors);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView broadcastModelAndView(final Message m) {
		ModelAndView res;

		res = this.broadcastModelAndView(m, null);

		return res;
	}

	protected ModelAndView broadcastModelAndView(final Message m, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("message/broadcast");

		res.addObject("mess", m);
		res.addObject("message", messageCode);

		return res;
	}

}
