
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ApplicationService;
import services.CompanyService;
import services.RookieService;
import services.MessageService;
import services.PositionService;
import domain.Actor;
import domain.Application;
import domain.Rookie;
import domain.Message;
import domain.Position;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private RookieService		rookieService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private MessageService		messageService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		Actor principal;
		ModelAndView res;
		Collection<Application> pendingApps = null;
		Collection<Application> submittedApps = null;
		Collection<Application> acceptedApps = null;
		Collection<Application> rejectedApps = null;

		principal = this.actorService.findByPrincipal();

		if (this.rookieService.findOneNoAssert(principal.getId()) != null) {
			pendingApps = this.applicationService.findApplicationsByStatusRookie("PENDING");
			submittedApps = this.applicationService.findApplicationsByStatusRookie("SUBMITTED");
			acceptedApps = this.applicationService.findApplicationsByStatusRookie("APPROVED");
			rejectedApps = this.applicationService.findApplicationsByStatusRookie("REJECTED");
		} else if (this.companyService.findOneNoAssert(principal.getId()) != null) {
			pendingApps = this.applicationService.findApplicationsByStatusCompany("PENDING");
			submittedApps = this.applicationService.findApplicationsByStatusCompany("SUBMITTED");
			acceptedApps = this.applicationService.findApplicationsByStatusCompany("APPROVED");
			rejectedApps = this.applicationService.findApplicationsByStatusCompany("REJECTED");
		}

		res = new ModelAndView("application/list");
		res.addObject("pendingApps", pendingApps);
		res.addObject("submittedApps", submittedApps);
		res.addObject("acceptedApps", acceptedApps);
		res.addObject("rejectedApps", rejectedApps);
		res.addObject("requestURI", "application/list.do");

		return res;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {

		ModelAndView result;
		final Application application;

		application = this.applicationService.findOne(applicationId);
		final Rookie rookie = application.getRookie();

		final Message notification = new Message();

		notification.setSubject("Your application has been accepted.");
		notification.setBody("Your application to " + application.getPosition().getTitle() + " has been accepted.");
		final Date moment = new Date();
		notification.setMoment(moment);
		final Collection<String> tags = new ArrayList<String>();
		notification.setTags(tags);
		notification.setSender(this.actorService.findByUserAccount(rookie.getUserAccount()));
		notification.setReceiver(this.actorService.findByUserAccount(rookie.getUserAccount()));

		this.messageService.save(notification);

		this.applicationService.acceptApplication(applicationId);

		result = new ModelAndView("redirect:/application/list.do");

		return result;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam final int applicationId) {

		ModelAndView result;
		final Application application;

		application = this.applicationService.findOne(applicationId);
		final Rookie rookie = application.getRookie();

		final Message notification = new Message();

		notification.setSubject("Your application has been rejected.");
		notification.setBody("Your application to " + application.getPosition().getTitle() + " has been rejected.");
		final Date moment = new Date();
		notification.setMoment(moment);
		final Collection<String> tags = new ArrayList<String>();
		notification.setTags(tags);
		notification.setSender(this.actorService.findByUserAccount(rookie.getUserAccount()));
		notification.setReceiver(this.actorService.findByUserAccount(rookie.getUserAccount()));

		this.messageService.save(notification);

		this.applicationService.rejectApplication(applicationId);

		result = new ModelAndView("redirect:/application/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int applicationId) {

		ModelAndView result;
		final Application app;

		app = this.applicationService.findOne(applicationId);
		result = new ModelAndView("application/show");
		result.addObject("application", app);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Application app;

		final Collection<Position> positions = this.positionService.findAll();

		final Collection<Position> positionsWithProblems = new ArrayList<Position>();

		for (final Position p : positions)
			if (!(this.positionService.findAllProblemsByPosition(p.getId()).isEmpty()))
				positionsWithProblems.add(p);

		app = this.applicationService.create();
		res = this.createEditModelAndView(app);
		res.addObject("positions", positionsWithProblems);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application a, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(a);
		} else
			try {
				this.applicationService.save(a);
				res = new ModelAndView("redirect:/application/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(a, "application.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView res;
		Application a;

		a = this.applicationService.findOneToEdit(applicationId);

		res = this.createEditModelAndView(a);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Application a, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(a);
		} else
			try {
				this.applicationService.save(a);
				res = new ModelAndView("redirect:/application/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(a, "application.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int applicationId) {

		ModelAndView res;

		this.applicationService.delete(applicationId);

		res = new ModelAndView("redirect:/application/list.do");
		return res;
	}

	protected ModelAndView createEditModelAndView(final Application app) {
		ModelAndView res;

		res = this.createEditModelAndView(app, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Application app, final String messageCode) {
		ModelAndView res;

		if (app.getId() == 0)
			res = new ModelAndView("application/create");
		else
			res = new ModelAndView("application/edit");
		res.addObject("application", app);
		res.addObject("message", messageCode);

		return res;
	}

}
