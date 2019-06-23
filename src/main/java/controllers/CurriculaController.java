
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CurriculaService;
import services.RookieService;
import domain.Actor;
import domain.Curricula;
import domain.Rookie;
import forms.CurriculaForm;

@Controller
@RequestMapping("/curricula")
public class CurriculaController extends AbstractController {

	@Autowired
	private RookieService		rookieService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CurriculaService	curriculaService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);
		final Rookie rookie = this.rookieService.findByPrincipal();

		final Collection<Curricula> curriculas = rookie.getCurriculas();

		res = new ModelAndView("curricula/list");
		res.addObject("curriculas", curriculas);
		res.addObject("requestURI", "curricula/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createCurriculum() {
		ModelAndView result;

		final CurriculaForm form = new CurriculaForm();

		result = this.createEditModelAndView(form);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculaId) {
		ModelAndView res;
		Curricula curricula;

		curricula = this.curriculaService.findOneToEdit(curriculaId);
		res = this.createEditModelAndView(curricula);

		res.addObject("curricula", curricula);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int curriculaId) {
		ModelAndView result;
		Curricula curricula;
		curricula = this.curriculaService.findOne(curriculaId);

		result = new ModelAndView("curricula/show");
		result.addObject("personalData", curricula.getPersonalData());
		result.addObject("miscellaneousData", curricula.getMiscellaneousData());
		result.addObject("educationsData", curricula.getEducationsData());
		result.addObject("positionsData", curricula.getPositionsData());
		result.addObject("curricula", curricula);
		result.addObject("curriculaId", curriculaId);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int curriculaId) {
		ModelAndView result;

		Curricula res;

		res = this.curriculaService.findOne(curriculaId);

		this.curriculaService.delete(res);
		result = new ModelAndView("redirect:/curricula/list.do");

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("form") @Valid final CurriculaForm form, final BindingResult binding) {

		ModelAndView res;
		Curricula c;

		c = this.curriculaService.reconstruct(form, binding);

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(c);
		} else
			try {
				this.curriculaService.save(c);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(form, "curricula.commit.error");

			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Curricula sp) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Curricula sp, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("curricula/create");
		else
			res = new ModelAndView("curricula/edit");
		res.addObject("curricula", sp);
		res.addObject("message", messageCode);

		return res;
	}

	private ModelAndView createEditModelAndView(final CurriculaForm form) {
		return this.createEditModelAndView(form, null);
	}

	private ModelAndView createEditModelAndView(final CurriculaForm form, final String message) {
		ModelAndView result;

		result = new ModelAndView("curricula/create");
		result.addObject("form", form);
		result.addObject("message", message);

		return result;
	}

}
