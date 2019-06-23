
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountRepository;
import services.ConfigurationsService;
import services.CreditCardService;
import services.ProviderService;
import domain.Configurations;
import domain.CreditCard;
import domain.Provider;
import forms.ProviderForm;

@Controller
@RequestMapping("/provider")
public class ProviderController extends AbstractController {

	@Autowired
	private ProviderService			providerService;

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

	// List ----------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Provider> providers;

		try {
			providers = this.providerService.findAll();
			result = new ModelAndView("provider/list");
			result.addObject("providers", providers);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int providerId) {
		ModelAndView result;
		Provider p;
		p = this.providerService.findOne(providerId);

		result = new ModelAndView("provider/show");
		result.addObject("provider", p);
		result.addObject("providerid", providerId);

		return result;
	}

	//Create ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ProviderForm pro;

		try {
			pro = new ProviderForm();
			result = new ModelAndView("provider/create");
			result.addObject("providerForm", pro);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("providerForm") @Valid final ProviderForm providerForm, final BindingResult binding) {
		ModelAndView result;
		Provider provider;

		provider = this.providerService.reconstruct(providerForm, binding);
		final CreditCard creditCard = provider.getCreditCard();
		final Configurations configurations = this.configurationsService.getConfiguration();
		final Collection<String> brands = configurations.getBrandNames();

		if (!providerForm.getUserAccount().getPassword().equals(providerForm.getConfirmPassword()))
			if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
				binding.addError(new FieldError("providerForm", "confirmPassword", providerForm.getConfirmPassword(), false, null, null, "Las contraseñas deben coincidir"));
				return this.createEditModelAndView(providerForm);
			}

			else {
				binding.addError(new FieldError("providerForm", "confirmPassword", providerForm.getConfirmPassword(), false, null, null, "Passwords must be the same"));
				return this.createEditModelAndView(providerForm);
			}

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("provider/create");
			result.addObject("providerForm", providerForm);
		}

		else
			try {

				this.providerService.save(provider);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(provider);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(providerForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(providerForm, "provider.duplicated.username");
				else
					result = this.createEditModelAndView(providerForm, "provider.registration.error");
			}
		return result;
	}

	//Edit --------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Provider provider;

		try {
			provider = this.providerService.findByPrincipal();

			result = new ModelAndView("provider/edit");
			result.addObject("provider", provider);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("provider") final Provider provider, final BindingResult binding) {
		ModelAndView result;
		final Provider pro;

		pro = this.providerService.reconstruct(provider, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = this.editModelAndView(pro);
			result.addObject("provider", provider);
		}

		else
			try {
				this.providerService.save(pro);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				System.out.println(pro);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.editModelAndView(pro, "rookie.registration.error");
			}
		return result;
	}

	//Ancillary methods -------------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final ProviderForm providerForm) {
		ModelAndView result;

		result = this.createEditModelAndView(providerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ProviderForm providerForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("provider/create");
		result.addObject("providerForm", providerForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final Provider provider) {
		ModelAndView result;

		result = this.editModelAndView(provider, null);

		return result;
	}

	protected ModelAndView editModelAndView(final Provider provider, final String message) {
		ModelAndView result;

		result = new ModelAndView("provider/edit");
		result.addObject("provider", provider);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

}
