
package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountRepository;
import services.ConfigurationsService;
import services.RookieService;
import domain.Configurations;
import domain.CreditCard;
import domain.Rookie;
import forms.RookieForm;

@Controller
@RequestMapping("/rookie")
public class RookieController extends AbstractController {

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private ConfigurationsService	configurationsService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rookie> bros;

		try {
			bros = this.rookieService.findAll();
			result = new ModelAndView("rookie/list");
			result.addObject("rookies", bros);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Register ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		RookieForm bro;

		try {
			bro = new RookieForm();
			result = new ModelAndView("rookie/create");
			result.addObject("rookieForm", bro);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Save the new rookie ------------------------------------------------------------------------------------

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Rookie rookie;

		try {
			rookie = this.rookieService.findByPrincipal();

			result = new ModelAndView("rookie/edit");
			result.addObject("rookie", rookie);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("rookieForm") @Valid final RookieForm rookieForm, final BindingResult binding) {
		ModelAndView result;
		Rookie rookie;

		rookie = this.rookieService.reconstruct(rookieForm, binding);
		final CreditCard creditCard = rookie.getCreditCard();
		final Configurations configurations = this.configurationsService.getConfiguration();
		final Collection<String> brands = configurations.getBrandNames();

		if (!rookieForm.getUserAccount().getPassword().equals(rookieForm.getConfirmPassword()))
			if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
				binding.addError(new FieldError("rookieForm", "confirmPassword", rookieForm.getConfirmPassword(), false, null, null, "Las contraseñas deben coincidir"));
				return this.createEditModelAndView(rookieForm);
			}

			else {
				binding.addError(new FieldError("rookieForm", "confirmPassword", rookieForm.getConfirmPassword(), false, null, null, "Passwords must be the same"));
				return this.createEditModelAndView(rookieForm);
			}

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("rookie/create");
			result.addObject("rookieForm", rookieForm);
		}

		else
			try {

				this.rookieService.save(rookie);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(rookie);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(rookieForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(rookieForm, "rookie.duplicated.username");
				else
					result = this.createEditModelAndView(rookieForm, "rookie.registration.error");
			}
		return result;
	}
	// SAVE ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("rookie") final Rookie prune, final BindingResult binding) {
		ModelAndView result;
		final Rookie rookie;

		rookie = this.rookieService.reconstruct(prune, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = this.editModelAndView(rookie);
			result.addObject("rookie", prune);
		}

		else
			try {
				this.rookieService.save(rookie);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				System.out.println(rookie);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.editModelAndView(rookie, "rookie.registration.error");
			}
		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final RookieForm rookieForm) {
		ModelAndView result;

		result = this.createEditModelAndView(rookieForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final RookieForm rookieForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("rookie/create");
		result.addObject("rookieForm", rookieForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final Rookie rookie) {
		ModelAndView result;

		result = this.editModelAndView(rookie, null);

		return result;
	}

	protected ModelAndView editModelAndView(final Rookie rookie, final String message) {
		ModelAndView result;

		result = new ModelAndView("rookie/edit");
		result.addObject("rookie", rookie);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
