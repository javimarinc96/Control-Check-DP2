
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
import services.AuditService;
import services.CompanyService;
import services.PositionService;
import services.SponsorshipService;
import domain.Actor;
import domain.Audit;
import domain.Position;
import domain.Sponsorship;

@Controller
@RequestMapping("/position")
public class PositionController extends AbstractController {

	@Autowired
	private PositionService		positionService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private AuditService		auditService;

	@Autowired
	private SponsorshipService	sponsorshipService;


	@RequestMapping(value = "/anonymousList", method = RequestMethod.GET)
	public ModelAndView anonymousList(@RequestParam(required = false) final String searcher) {

		ModelAndView res;
		Collection<Position> positions;
		String requestURI = "position/anonymousList.do";
		Collection<Position> searchedPositions;

		positions = this.positionService.findAll();

		if (searcher != null) {
			searchedPositions = this.positionService.searcherPositions(searcher);
			positions.retainAll(searchedPositions);
			requestURI += "?searcher" + searcher;
		}

		res = new ModelAndView("position/list");
		res.addObject("positions", positions);
		res.addObject("requestURI", requestURI);

		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String searcher) {

		ModelAndView res;
		Collection<Position> positions;
		Actor principal;
		String requestURI = "position/list.do";
		Collection<Position> searchedPositions;

		principal = this.actorService.findByPrincipal();

		if (this.companyService.findOne(principal.getId()) != null)
			positions = this.positionService.findAllPositionsByCompany(principal.getId());
		else
			positions = this.positionService.findAll();

		if (searcher != null) {
			searchedPositions = this.positionService.searcherPositions(searcher);
			positions.retainAll(searchedPositions);
			requestURI += "?searcher" + searcher;
		}

		res = new ModelAndView("position/list");
		res.addObject("positions", positions);
		res.addObject("requestURI", requestURI);

		return res;
	}

	@RequestMapping(value = "/company/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Position p;

		p = this.positionService.create();
		res = this.createEditModelAndView(p);

		return res;
	}

	@RequestMapping(value = "/company/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionId) {
		ModelAndView res;
		Position p;

		p = this.positionService.findOneToEdit(positionId);
		res = this.createEditModelAndView(p);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int positionId) {
		ModelAndView result;
		final Sponsorship sponsorship = this.sponsorshipService.findRandomOneByPosition(positionId);
		Position p;
		p = this.positionService.findOne(positionId);
		final Collection<Audit> audits = this.auditService.getAuditsByPosition(positionId);
		final boolean deleteable = this.positionService.isDeleteable(positionId);
		result = new ModelAndView("position/show");
		result.addObject("position", p);
		result.addObject("deleteable", deleteable);
		result.addObject("positionid", positionId);
		result.addObject("sponsorship", sponsorship);
		result.addObject("audits", audits);

		return result;
	}
	@RequestMapping(value = "/company/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int positionId) {

		ModelAndView res;

		this.positionService.delete(positionId);

		res = new ModelAndView("redirect:/position/list.do");
		return res;
	}

	@RequestMapping(value = "/company/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Position p, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(p);
		} else
			try {
				this.positionService.save(p);
				res = new ModelAndView("redirect:/position/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(p, "position.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Position p) {
		ModelAndView res;

		res = this.createEditModelAndView(p, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Position p, final String messageCode) {
		ModelAndView res;

		if (p.getId() == 0)
			res = new ModelAndView("position/company/create");
		else
			res = new ModelAndView("position/company/edit");

		res.addObject("position", p);
		res.addObject("message", messageCode);

		return res;
	}

}
