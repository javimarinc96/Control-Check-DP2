
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationsService;
import controllers.AbstractController;
import domain.Configurations;

@Controller
@RequestMapping("/configurations/administrator")
public class ConfigurationsController extends AbstractController {

	@Autowired
	private ConfigurationsService	configurationsService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Configurations config;

		config = this.configurationsService.getConfiguration();
		res = this.createEditModelAndView(config);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Configurations config, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(config);
		} else
			try {
				this.configurationsService.save(config);
				res = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(config, "configurations.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Configurations config) {
		ModelAndView res;

		res = this.createEditModelAndView(config, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Configurations config, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("configurations/administrator/edit");

		res.addObject("config", config);
		res.addObject("message", messageCode);

		return res;
	}

}
