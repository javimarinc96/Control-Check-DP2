
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
import services.AuditorService;
import services.ConfigurationsService;
import services.CreditCardService;
import domain.Auditor;
import domain.Configurations;
import domain.CreditCard;
import forms.AuditorForm;

@Controller
@RequestMapping("/auditor")
public class AuditorController extends AbstractController {

	@Autowired
	private AuditorService			AuditorService;

	@Autowired
	private ConfigurationsService	configurationsService;

	@Autowired
	private CreditCardService		creditCardService;

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
		Collection<Auditor> bros;

		try {
			bros = this.AuditorService.findAll();
			result = new ModelAndView("auditor/list");
			result.addObject("auditors", bros);
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
		AuditorForm bro;

		try {
			bro = new AuditorForm();
			result = new ModelAndView("auditor/create");
			result.addObject("auditorForm", bro);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Auditor Auditor;

		try {
			Auditor = this.AuditorService.findByPrincipal();
			result = new ModelAndView("auditor/edit");
			result.addObject("auditor", Auditor);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Save the new Auditor ------------------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("auditorForm") @Valid final AuditorForm AuditorForm, final BindingResult binding) {
		ModelAndView result;
		Auditor Auditor;

		Auditor = this.AuditorService.reconstruct(AuditorForm, binding);
		final CreditCard creditCard = Auditor.getCreditCard();
		final Configurations configurations = this.configurationsService.getConfiguration();
		final Collection<String> brands = configurations.getBrandNames();

		if (!AuditorForm.getUserAccount().getPassword().equals(AuditorForm.getConfirmPassword()))
			if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
				binding.addError(new FieldError("auditorForm", "confirmPassword", AuditorForm.getConfirmPassword(), false, null, null, "Las contraseñas deben coincidir"));
				return this.createEditModelAndView(AuditorForm);
			}

			else {
				binding.addError(new FieldError("auditorForm", "confirmPassword", AuditorForm.getConfirmPassword(), false, null, null, "Passwords must be the same"));
				return this.createEditModelAndView(AuditorForm);
			}

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("auditor/create");
			result.addObject("auditorForm", AuditorForm);
		}

		else
			try {

				this.AuditorService.save(Auditor);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(Auditor);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(AuditorForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(AuditorForm, "auditor.duplicated.username");
				else
					result = this.createEditModelAndView(AuditorForm, "auditor.registration.error");
			}

		return result;

	}

	// SAVE EDIT ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("Auditor") final Auditor prune, final BindingResult binding) {
		ModelAndView result;
		final Auditor Auditor;

		Auditor = this.AuditorService.reconstruct(prune, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = this.editModelAndView(Auditor);
			result.addObject("auditor", prune);
		}

		else
			try {
				this.AuditorService.save(Auditor);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				System.out.println(Auditor);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.editModelAndView(Auditor, "auditor.registration.error");
			}
		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final AuditorForm AuditorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(AuditorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AuditorForm AuditorForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("auditor/create");
		result.addObject("auditorForm", AuditorForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final Auditor Auditor) {
		ModelAndView result;

		result = this.editModelAndView(Auditor, null);

		return result;
	}

	protected ModelAndView editModelAndView(final Auditor Auditor, final String message) {
		ModelAndView result;

		result = new ModelAndView("auditor/edit");
		result.addObject("auditor", Auditor);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
