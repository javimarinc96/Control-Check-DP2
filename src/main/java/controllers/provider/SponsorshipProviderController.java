
package controllers.provider;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import services.ProviderService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Position;
import domain.Provider;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship/provider")
public class SponsorshipProviderController extends AbstractController {

	//Constructor --------------------------

	public SponsorshipProviderController() {
		super();
	}


	//Services ----------------------

	@Autowired
	private SponsorshipService	sponsorshipService;

	@Autowired
	private ProviderService		providerService;

	@Autowired
	private PositionService		positionService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final Sponsorship sponsorship = this.sponsorshipService.create();

		final ModelAndView result = this.createEditModelAndView(sponsorship);
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Sponsorship sponsorship, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(sponsorship);
			System.out.println(binding.getAllErrors());
		} else
			try {

				System.out.println(binding.getAllErrors());
				this.sponsorshipService.save(sponsorship);

				result = new ModelAndView("redirect:/sponsorship/provider/list.do");

			} catch (final Throwable oops) {
				//System.out.println(oops);
				result = this.createEditModelAndView(sponsorship, "sponsorship.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sponsorshipId) {
		ModelAndView res;
		Sponsorship sponsorship;

		sponsorship = this.sponsorshipService.findOneToEdit(sponsorshipId);
		res = this.createEditModelAndView(sponsorship);

		res.addObject("sponsorship", sponsorship);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Sponsorship sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp);
		} else
			try {
				this.sponsorshipService.save(sp);
				res = new ModelAndView("redirect:/sponsorship/provider/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, "sponsorship.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int sponsorshipId) {

		ModelAndView res;

		this.sponsorshipService.delete(sponsorshipId);

		res = new ModelAndView("redirect:/sponsorship/provider/list.do");
		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int sponsorshipId) {
		ModelAndView result;
		Sponsorship sponsorship;
		sponsorship = this.sponsorshipService.findOne(sponsorshipId);
		result = new ModelAndView("sponsorship/provider/show");
		result.addObject("sponsorship", sponsorship);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Sponsorship> sponsorships = new ArrayList<Sponsorship>();
		final Provider provider = this.providerService.findByPrincipal();
		sponsorships = this.sponsorshipService.findSponsorshipsByProvider(provider.getId());
		result = new ModelAndView("sponsorship/provider/list");
		result.addObject("sponsorships", sponsorships);

		return result;
	}

	private ModelAndView createEditModelAndView(final Sponsorship sponsorship) {

		return this.createEditModelAndView(sponsorship, null);
	}

	protected ModelAndView createEditModelAndView(final Sponsorship sp, final String messageCode) {
		ModelAndView res;
		final Collection<Position> positions = this.positionService.findAll();

		if (sp.getId() == 0)
			res = new ModelAndView("sponsorship/provider/create");
		else
			res = new ModelAndView("sponsorship/provider/edit");
		res.addObject("sponsorship", sp);
		res.addObject("message", messageCode);
		res.addObject("positions", positions);
		return res;
	}
}
