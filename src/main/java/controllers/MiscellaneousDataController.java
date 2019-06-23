
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MiscellaneousDataService;
import domain.MiscellaneousData;

@Controller
@RequestMapping("/miscellaneousData")
public class MiscellaneousDataController extends AbstractController {

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculaId) {
		ModelAndView res;
		MiscellaneousData miscellaneousData;

		miscellaneousData = this.miscellaneousDataService.create(curriculaId);
		res = this.createEditModelAndView(miscellaneousData);
		res.addObject("miscellaneousData", miscellaneousData);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousDataId) {
		ModelAndView res;
		MiscellaneousData miscellaneousData;

		miscellaneousData = this.miscellaneousDataService.findOneToEdit(miscellaneousDataId);
		res = this.createEditModelAndView(miscellaneousData);

		res.addObject("miscellaneousData", miscellaneousData);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int miscellaneousDataId) {
		ModelAndView result;
		MiscellaneousData miscellaneousData;
		miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
		result = new ModelAndView("miscellaneousData/show");
		result.addObject("miscellaneousData", miscellaneousData);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscellaneousData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.miscellaneousDataService.save(sp);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "miscellaneousData.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final MiscellaneousData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.miscellaneousDataService.save(sp);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "miscellaneousData.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousData sp) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousData sp, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("miscellaneousData/create");
		else
			res = new ModelAndView("miscellaneousData/edit");
		res.addObject("miscellaneousData", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
