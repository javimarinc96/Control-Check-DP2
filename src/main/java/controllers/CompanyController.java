
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
import services.CompanyService;
import services.ConfigurationsService;
import services.CreditCardService;
import services.PositionService;
import domain.Company;
import domain.Configurations;
import domain.CreditCard;
import domain.Position;
import forms.CompanyForm;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	@Autowired
	private CompanyService			companyService;

	@Autowired
	private PositionService			positionService;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private ConfigurationsService	configurationsService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Company> bros;

		try {
			bros = this.companyService.findAll();
			result = new ModelAndView("company/list");
			result.addObject("companys", bros);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/positions", method = RequestMethod.GET)
	public ModelAndView listPositions(@RequestParam final int companyId) {
		ModelAndView result;
		final Company company = this.companyService.findOne(companyId);
		final Collection<Position> positions = this.positionService.findAllPositionsByCompany(companyId);
		try {
			result = new ModelAndView("company/positions");
			result.addObject("positions", positions);
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
		CompanyForm bro;

		try {
			bro = new CompanyForm();
			result = new ModelAndView("company/create");
			result.addObject("companyForm", bro);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Save the new company ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("companyForm") @Valid final CompanyForm companyForm, final BindingResult binding) {
		ModelAndView result;
		Company company;

		company = this.companyService.reconstruct(companyForm, binding);
		final CreditCard creditCard = company.getCreditCard();
		final Configurations configurations = this.configurationsService.getConfiguration();
		final Collection<String> brands = configurations.getBrandNames();

		if (!companyForm.getUserAccount().getPassword().equals(companyForm.getConfirmPassword()))
			if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
				binding.addError(new FieldError("companyForm", "confirmPassword", companyForm.getConfirmPassword(), false, null, null, "Las contraseñas deben coincidir"));
				return this.createEditModelAndView(companyForm);
			}

			else {
				binding.addError(new FieldError("companyForm", "confirmPassword", companyForm.getConfirmPassword(), false, null, null, "Passwords must be the same"));
				return this.createEditModelAndView(companyForm);
			}

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("company/create");
			result.addObject("companyForm", companyForm);
		}

		else
			try {

				this.companyService.save(company);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(company);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(companyForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(companyForm, "company.duplicated.username");
				else
					result = this.createEditModelAndView(companyForm, "company.registration.error");
			}
		return result;
	}
	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Company company;

		try {
			company = this.companyService.findByPrincipal();

			result = new ModelAndView("company/edit");
			result.addObject("company", company);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// SAVE ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("company") final Company prune, final BindingResult binding) {
		ModelAndView result;
		final Company company;

		company = this.companyService.reconstruct(prune, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.editModelAndView(company);
			result.addObject("company", prune);
		}

		else
			try {
				this.companyService.save(company);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				System.out.println(company);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.editModelAndView(company, "company.registration.error");
			}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int companyId) {
		ModelAndView result;
		Company m;
		m = this.companyService.findOne(companyId);
		result = new ModelAndView("company/show");
		result.addObject("company", m);

		return result;
	}

	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ModelAndView obtainScore(@RequestParam final int companyId) {
		ModelAndView result;

		final Company c = this.companyService.findOne(companyId);
		final Double score = this.companyService.obtainScore(companyId);
		final Double homotheticScore = score / 10;
		c.setScore(homotheticScore);

		result = this.list();

		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final CompanyForm companyForm) {
		ModelAndView result;

		final Collection<String> brands = this.configurationsService.getConfiguration().getBrandNames();
		result = this.createEditModelAndView(companyForm, null);
		result.addObject("brands", brands);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CompanyForm companyForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("company/create");
		result.addObject("companyForm", companyForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final Company company) {
		ModelAndView result;

		//		final Collection<String> brands = this.configurationsService.getConfiguration().getBrandNames();

		result = this.editModelAndView(company, null);
		//		result.addObject("brands", brands);

		return result;
	}

	protected ModelAndView editModelAndView(final Company company, final String message) {
		ModelAndView result;

		result = new ModelAndView("company/edit");
		result.addObject("company", company);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

}
