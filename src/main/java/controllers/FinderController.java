
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FinderService;
import services.RookieService;
import domain.Finder;
import domain.Rookie;
import domain.Position;

@Controller
@RequestMapping("/finder")
public class FinderController extends AbstractController {

	// Constructors --------------------------------------

	public FinderController() {
		super();

	}


	// Services -------------------------------------------

	@Autowired
	private FinderService	finderService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private RookieService	rookieService;


	// Show -----------------------------------------------
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView result;
		Finder finder;

		final Rookie rookie = this.rookieService.findByPrincipal();

		finder = rookie.getFinder();

		this.finderService.finderUpdate(finder);
		final Collection<Position> positions = this.finderService.finderResults(finder);

		result = new ModelAndView("finder/show");
		result.addObject("finder", finder);
		result.addObject("positions", positions);

		return result;
	}
	// Edit -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Finder finder;

		final Rookie rookie = this.rookieService.findByPrincipal();

		finder = rookie.getFinder();

		Assert.notNull(finder);
		res = this.createEditModelAndView(finder);

		return res;
	}

	// Save edit --------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(finder);
		else
			try {
				this.finderService.save(finder);
				res = new ModelAndView("redirect:/finder/show.do");

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(finder, "finder.commit.error");
			}

		return res;
	}

	// Ancillary methods ------------------------------------

	private ModelAndView createEditModelAndView(final Finder finder) {

		return this.createEditModelAndView(finder, null);
	}

	private ModelAndView createEditModelAndView(final Finder finder, final String message) {

		final ModelAndView res = new ModelAndView("finder/edit");
		res.addObject("finder", finder);
		res.addObject("message", message);

		return res;

	}

}
