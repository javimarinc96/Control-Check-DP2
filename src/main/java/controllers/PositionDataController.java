
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculaService;
import services.PositionDataService;
import domain.Curricula;
import domain.PositionData;

@Controller
@RequestMapping("/positionData")
public class PositionDataController extends AbstractController {

	@Autowired
	private PositionDataService	positionDataService;

	@Autowired
	private CurriculaService	curriculaService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int curriculaId) {

		ModelAndView res;

		final Curricula curricula = this.curriculaService.findOne(curriculaId);
		res = new ModelAndView("positionData/list");
		res.addObject("positionDatas", curricula.getPositionsData());
		res.addObject("requestURI", "positionData/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculaId) {
		ModelAndView res;
		PositionData positionData;

		positionData = this.positionDataService.create(curriculaId);
		
		res = this.createEditModelAndView(positionData,curriculaId);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionDataId) {
		ModelAndView res;
		PositionData positionData;

		positionData = this.positionDataService.findOneToEdit(positionDataId);
		res = this.editModelAndView(positionData);

		res.addObject("positionData", positionData);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int positionDataId) {
		ModelAndView result;
		PositionData positionData;
		positionData = this.positionDataService.findOne(positionDataId);
		result = new ModelAndView("positionData/show");
		result.addObject("positionData", positionData);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int positionDataId) {
		ModelAndView result;

		PositionData res;

		res = this.positionDataService.findOne(positionDataId);

		this.positionDataService.delete(res);
		result = new ModelAndView("redirect:/curricula/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int curriculaId, @Valid final PositionData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp,curriculaId);
		} else
			try {
				this.positionDataService.save(sp, curriculaId);
				res = new ModelAndView("redirect:/curricula/list.do");
				res.addObject("curriculaId", curriculaId);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp,curriculaId, "positionData.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final PositionData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.editModelAndView(sp);
		} else
			try {
				this.positionDataService.saveEdit(sp);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(sp, "positionData.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final PositionData sp, final int curriculaId) {
		ModelAndView res;

		res = this.createEditModelAndView(sp,curriculaId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final PositionData sp,final int curriculaId, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("positionData/create");
		res.addObject("positionData", sp);
		res.addObject("curriculaId", curriculaId);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView editModelAndView(final PositionData sp) {
		ModelAndView res;

		res = this.editModelAndView(sp, null);

		return res;
	}

	protected ModelAndView editModelAndView(final PositionData sp, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("positionData/edit");
		res.addObject("positionData", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
