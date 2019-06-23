
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialProfileService;
import domain.Actor;
import domain.SocialProfile;

@Controller
@RequestMapping("/socialProfile")
public class SocialProfileController extends AbstractController {

	@Autowired
	private ActorService			actorService;

	@Autowired
	private SocialProfileService	socialProfileService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;

		final Actor principal = this.actorService.findByPrincipal();

		final Collection<SocialProfile> socialProfiles = principal.getSocialProfiles();

		res = new ModelAndView("socialProfile/list");
		res.addObject("socialProfiles", socialProfiles);
		res.addObject("requestURI", "socialProfile/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		SocialProfile socialProfile;

		socialProfile = this.socialProfileService.create();
		res = this.createEditModelAndView(socialProfile);
		res.addObject("socialProfile", socialProfile);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialProfileId) {
		ModelAndView res;
		SocialProfile socialProfile;

		socialProfile = this.socialProfileService.findOneToEdit(socialProfileId);
		res = this.createEditModelAndView(socialProfile);

		res.addObject("socialProfile", socialProfile);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int socialProfileId) {
		ModelAndView result;
		final Actor actor = this.actorService.findByPrincipal();
		final String photo = actor.getPhoto();
		SocialProfile socialProfile;
		socialProfile = this.socialProfileService.findOne(socialProfileId);
		result = new ModelAndView("socialProfile/show");
		result.addObject("socialProfile", socialProfile);
		result.addObject("photo", photo);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SocialProfile socialProfile, final BindingResult binding) {
		ModelAndView result;

		try {

			this.socialProfileService.delete(socialProfile.getId());
			result = new ModelAndView("redirect:/socialProfile/list.do");
			result.addObject("socialProfileId", socialProfile.getId());

		}

		catch (final Throwable oops) {
			result = this.createEditModelAndView(socialProfile, "socialProfile.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialProfile sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.socialProfileService.save(sp);
				res = new ModelAndView("redirect:/socialProfile/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "socialProfile.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final SocialProfile sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.socialProfileService.save(sp);
				res = new ModelAndView("redirect:/socialProfile/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "socialProfile.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/forgetMe", method = RequestMethod.GET)
	public ModelAndView delete() {

		ModelAndView res;

		this.actorService.deleteActor();

		res = new ModelAndView("redirect:/security/login.do");
		return res;
	}

	@RequestMapping(value = "/exportData", method = RequestMethod.GET)
	public ModelAndView exportData() {

		ModelAndView res;

		final String json = this.actorService.exportPersonalData();

		res = new ModelAndView("socialProfile/exportData");
		res.addObject("json", json);

		return res;
	}

	protected ModelAndView createEditModelAndView(final SocialProfile sp) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final SocialProfile sp, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("socialProfile/create");
		else
			res = new ModelAndView("socialProfile/edit");
		res.addObject("socialProfile", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
