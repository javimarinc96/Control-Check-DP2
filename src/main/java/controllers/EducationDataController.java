
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CurriculaService;
import services.EducationDataService;
import services.RookieService;
import domain.Actor;
import domain.Curricula;
import domain.EducationData;
import domain.Rookie;
import domain.PositionData;

@Controller
@RequestMapping("/educationData")
public class EducationDataController extends AbstractController {

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private EducationDataService	educationDataService;

	@Autowired
	private CurriculaService		curriculaService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int curriculaId) {

		ModelAndView res;

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);
		final Curricula curricula = this.curriculaService.findOne(curriculaId);
		res = new ModelAndView("educationData/list");
		res.addObject("educationDatas", curricula.getEducationsData());
		res.addObject("requestURI", "educationData/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculaId) {
		ModelAndView res;
		EducationData educationData;

		educationData = this.educationDataService.create(curriculaId);
		res = this.createEditModelAndView(educationData,curriculaId);


		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationDataId) {
		ModelAndView res;
		EducationData educationData;

		educationData = this.educationDataService.findOneToEdit(educationDataId);
		res = this.editModelAndView(educationData);

		res.addObject("educationData", educationData);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int educationDataId) {
		ModelAndView result;
		EducationData educationData;
		educationData = this.educationDataService.findOne(educationDataId);
		result = new ModelAndView("educationData/show");
		result.addObject("educationData", educationData);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int educationDataId) {
		ModelAndView result;

		EducationData res;

		res = this.educationDataService.findOne(educationDataId);

		this.educationDataService.delete(res);
		result = new ModelAndView("redirect:/curricula/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int curriculaId, @Valid final EducationData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp,curriculaId);
		} else
			try {
				this.educationDataService.save(sp, curriculaId);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp,curriculaId, "educationData.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final EducationData sp, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.editModelAndView(sp);
		} else
			try {
				this.educationDataService.saveEdit(sp);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(sp, "educationData.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final EducationData sp,final int curriculaId) {
		ModelAndView res;

		res = this.createEditModelAndView(sp,curriculaId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final EducationData sp,final int curriculaId, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("educationData/create");
		else
			res = new ModelAndView("educationData/edit");
		res.addObject("educationData", sp);
		res.addObject("curriculaId", curriculaId);
		res.addObject("message", messageCode);

		return res;
	}
	
	protected ModelAndView editModelAndView(final EducationData sp) {
		ModelAndView res;

		res = this.editModelAndView(sp, null);

		return res;
	}

	protected ModelAndView editModelAndView(final EducationData sp, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("educationData/edit");
		res.addObject("educationData", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
