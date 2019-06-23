/*
 * 
 * AdministratorController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountRepository;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationsService;
import services.MessageService;
import domain.Actor;
import domain.Administrator;
import domain.Message;
import domain.Provider;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	// Services -------------------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private ConfigurationsService	configurationsService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	/* Crear administrador con objeto form */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		AdministratorForm admin;

		try {
			admin = new AdministratorForm();
			result = new ModelAndView("administrator/create");
			result.addObject("administratorForm", admin);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("administratorForm") @Valid final AdministratorForm administratorForm, final BindingResult binding) {
		ModelAndView result;
		Administrator admin;

		admin = this.administratorService.reconstruct(administratorForm, binding);

		if (!administratorForm.getUserAccount().getPassword().equals(administratorForm.getConfirmPassword()))
			if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
				binding.addError(new FieldError("administratorForm", "confirmPassword", administratorForm.getConfirmPassword(), false, null, null, "Las contraseñas deben coincidir"));
				return this.createEditModelAndView(administratorForm);
			}

			else {
				binding.addError(new FieldError("administratorForm", "confirmPassword", administratorForm.getConfirmPassword(), false, null, null, "Passwords must be the same"));
				return this.createEditModelAndView(administratorForm);
			}

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("administrator/create");
			result.addObject("administratorForm", administratorForm);
		} else
			try {

				this.administratorService.save(admin);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(admin);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(administratorForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(administratorForm, "admin.duplicated.username");
				else
					result = this.createEditModelAndView(administratorForm, "admin.registration.error");
			}
		return result;

	}

	// Dashboard -----------------------------------------------------------
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		final ModelAndView result;

		// Queries
		final Double avgSalary = this.administratorService.avgAuditScore();
		final int maxSalary = this.administratorService.maxAuditScore();
		final int minSalary = this.administratorService.minAuditScore();
		final Double stddevSalary = this.administratorService.stddevAuditScore();

		final Double avgCurriculas = this.administratorService.avgScoreCompany();
		final Double maxCurriculas = this.administratorService.maxScoreCompany();
		final Double minCurriculas = this.administratorService.minScoreCompany();
		final Double stddevCurriculas = this.administratorService.stddevScoreCompany();

		final Double avgResults = this.administratorService.avgItemsProvider();
		final Double maxResults = this.administratorService.minItemsProvider();
		final Double minResults = this.administratorService.maxItemsProvider();
		final Double stddevResults = this.administratorService.stddevItemsProvider();

		final Double avgPositions = this.administratorService.avgPositions();
		final Double maxPositions = this.administratorService.maxPositions();
		final Double minPositions = this.administratorService.minPositions();
		final Double stddevPositions = this.administratorService.stddevPositions();

		final Double avgAppRookie = this.administratorService.avgAppRookie();
		final Double maxAppRookie = this.administratorService.maxAppRookie();
		final Double minAppRookie = this.administratorService.minAppRookie();
		final Double stddevAppRookie = this.administratorService.stddevAppRookie();
		
		final Double avgSponsorshipProvider = this.administratorService.avgSponsorshipProvider();
		final Integer maxSponsorshipProvider = this.administratorService.maxSponsorshipProvider();
		final Integer minSponsorshipProvider = this.administratorService.minSponsorshipProvider();
		final Double stddevSponsorshipProvider = this.administratorService.stddevSponsorshipProvider();
		
		final Double avgSponsorshipPosition = this.administratorService.avgSponsorshipPosition();
		final Integer maxSponsorshipPosition = this.administratorService.maxSponsorshipPosition();
		final Integer minSponsorshipPosition = this.administratorService.minSponsorshipPosition();
		final Double stddevSponsorshipPosition = this.administratorService.stddevSponsorshipPosition();
		
		final Collection<Provider> aboveAvgSponsorships = this.administratorService.aboveAvgSponsorships();

		result = new ModelAndView("administrator/dashboard");

		result.addObject("avgPositions", avgPositions);
		result.addObject("maxPositions", maxPositions);
		result.addObject("minPositions", minPositions);
		result.addObject("stddevPositions", stddevPositions);

		result.addObject("avgAppRookie", avgAppRookie);
		result.addObject("maxAppRookie", maxAppRookie);
		result.addObject("minAppRookie", minAppRookie);
		result.addObject("stddevAppRookie", stddevAppRookie);

		result.addObject("avgSalary", avgSalary);
		result.addObject("maxSalary", maxSalary);
		result.addObject("minSalary", minSalary);
		result.addObject("stddevSalary", stddevSalary);

		result.addObject("avgCurriculas", avgCurriculas);
		result.addObject("maxCurriculas", maxCurriculas);
		result.addObject("minCurriculas", minCurriculas);
		result.addObject("stddevCurriculas", stddevCurriculas);

		result.addObject("avgResults", avgResults);
		result.addObject("maxResults", maxResults);
		result.addObject("minResults", minResults);
		result.addObject("stddevResults", stddevResults);

		result.addObject("avgSalary", avgSalary);
		result.addObject("maxSalary", maxSalary);
		result.addObject("minSalary", minSalary);
		result.addObject("stddevSalary", stddevSalary);

		result.addObject("avgCurriculas", avgCurriculas);
		result.addObject("maxCurriculas", maxCurriculas);
		result.addObject("minCurriculas", minCurriculas);
		result.addObject("stddevCurriculas", stddevCurriculas);
		result.addObject("avgResults", avgResults);
		result.addObject("maxResults", maxResults);
		result.addObject("minResults", minResults);
		result.addObject("stddevResults", stddevResults);
		result.addObject("avgSalary", avgSalary);
		result.addObject("maxSalary", maxSalary);
		result.addObject("minSalary", minSalary);
		
		result.addObject("avgSponsorshipsProvider", avgSponsorshipProvider);
		result.addObject("maxSponsorshipsProvider", maxSponsorshipProvider);
		result.addObject("minSponsorshipsProvider", minSponsorshipProvider);
		result.addObject("stddevSponsorshipsProvider", stddevSponsorshipProvider);
		
		result.addObject("avgSponsorshipsPosition", avgSponsorshipPosition);
		result.addObject("maxSponsorshipsPosition", maxSponsorshipPosition);
		result.addObject("minSponsorshipsPosition", minSponsorshipPosition);
		result.addObject("stddevSponsorshipsPosition", stddevSponsorshipPosition);
		
		result.addObject("aboveAvgSponsorships", aboveAvgSponsorships);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	protected ModelAndView createEditModelAndView(final AdministratorForm administratorForm) {
		ModelAndView result;

		final Collection<String> brands = this.configurationsService.getConfiguration().getBrandNames();
		result = this.createEditModelAndView(administratorForm, null);
		result.addObject("brands", brands);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AdministratorForm companyForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/create");
		result.addObject("administratorForm", companyForm);
		result.addObject("message", message);

		return result;
	}

	// List spammers ----------------------------------------------------------

	@RequestMapping(value = "/spammers", method = RequestMethod.GET)
	public ModelAndView listSpammers() {
		ModelAndView res;

		final Collection<Actor> spammers = this.administratorService.getSpammers();

		res = new ModelAndView("administrator/spammers");
		res.addObject("requestURI", "administrator/spammers.do");
		res.addObject("spammers", spammers);

		return res;
	}

	//Compute spammers

	@RequestMapping(value = "/compspammers", method = RequestMethod.GET)
	public ModelAndView computeSpammers() {

		ModelAndView res;

		Actor principal;
		Collection<Message> messages;
		int spamMessages;
		final Collection<Actor> users = this.actorService.findAll();
		final Collection<String> spamWords = this.configurationsService.getConfiguration().getSpamWords();

		// Make sure that the principal is an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		for (final Actor user : users) {

			spamMessages = 0;
			messages = this.messageService.findAllOutboxMessagesByPrincipal(user.getId());

			if ((messages != null) && !messages.isEmpty())
				for (final Message message : messages)
					for (final String spamWord : spamWords)
						if (message.getBody().contains(spamWord) || message.getSubject().contains(spamWord))
							spamMessages++;

			if ((spamMessages != 0) && (spamMessages >= (messages.size() * 0.1)))
				user.setIsSpammer(true);
		}
		res = this.listSpammers();

		return res;
	}

	//Ban/unban --------------------------------------------------------------------

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int actorId) {

		final Actor banActor = this.actorService.findOne(actorId);
		this.administratorService.ban(banActor);
		final ModelAndView result = new ModelAndView("redirect:/administrator/spammers.do");
		return result;

	}

	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int actorId) {

		final Actor unbanActor = this.actorService.findOne(actorId);
		this.administratorService.unban(unbanActor);
		final ModelAndView result = new ModelAndView("redirect:/administrator/spammers.do");
		return result;

	}
}
