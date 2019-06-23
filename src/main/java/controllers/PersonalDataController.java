
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PersonalDataService;
import domain.Actor;
import domain.PersonalData;

@Controller
@RequestMapping("/personalData")
public class PersonalDataController extends AbstractController {

	@Autowired
	private PersonalDataService	personalDataService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculaId) {
		ModelAndView res;
		PersonalData personalData;

		personalData = this.personalDataService.create(curriculaId);
		res = this.createEditModelAndView(personalData);
		res.addObject("personalData", personalData);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int personalDataId) {
		ModelAndView res;
		PersonalData personalData;

		personalData = this.personalDataService.findOne(personalDataId);
		res = this.createEditModelAndView(personalData);

		res.addObject("personalData", personalData);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int personalDataId) {
		ModelAndView result;
		final Actor actor = this.actorService.findByPrincipal();
		final String photo = actor.getPhoto();
		PersonalData personalData;
		personalData = this.personalDataService.findOne(personalDataId);
		result = new ModelAndView("personalData/show");
		result.addObject("personalData", personalData);
		result.addObject("photo", photo);

		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final PersonalData personalData, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//
	//			this.personalDataService.delete(personalData);
	//			result = new ModelAndView("redirect:/personalData/list.do");
	//			result.addObject("personalDataId", personalData.getId());
	//
	//		}
	//
	//		catch (final Throwable oops) {
	//			result = this.createEditModelAndView(personalData, "personalData.commit.error");
	//		}
	//		return result;
	//	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PersonalData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.personalDataService.save(sp);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "personalData.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final PersonalData sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.personalDataService.save(sp);
				res = new ModelAndView("redirect:/curricula/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "personalData.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final PersonalData sp) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final PersonalData sp, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("personalData/create");
		else
			res = new ModelAndView("personalData/edit");
		res.addObject("personalData", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
