
package controllers.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuditorService;
import services.PositionService;
import services.AuditService;
import controllers.AbstractController;
import domain.Actor;
import domain.Auditor;
import domain.Position;
import domain.Audit;

@Controller
@RequestMapping("/audit/auditor")
public class AuditAuditorController extends AbstractController {

	@Autowired
	private AuditService	AuditService;
	@Autowired
	private PositionService	positionService;
	@Autowired
	private ActorService	actorService;
	@Autowired
	private AuditorService	AuditorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Audit> Audits;

		final int logueadoId = this.actorService.findByPrincipal().getId();
		Audits = this.AuditService.getAuditsByAuditor(logueadoId);

		res = new ModelAndView("audit/list");
		res.addObject("audits", Audits);
		res.addObject("requestURI", "audit/auditor/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Audit pro;

		final Collection<Position> positions = this.positionService.findFreePositions();

		pro = this.AuditService.create();
		res = this.createEditModelAndView(pro);
		res.addObject("positions", positions);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int auditId) {
		ModelAndView res;
		Audit a;

		a = this.AuditService.findOne(auditId);
		res = this.createEditModelAndView(a);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Auditor.class, principal);
		final Auditor aud = this.AuditorService.findByPrincipal();

		if (a.getDraftMode() == false || !a.getAuditor().equals(aud))
			res = new ModelAndView("redirect:list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int auditId) {
		ModelAndView result;
		Audit a;
		a = this.AuditService.findOne(auditId);
		result = new ModelAndView("audit/show");
		result.addObject("audit", a);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int auditId) {

		ModelAndView res;
		Audit a;
		a = this.AuditService.findOne(auditId);
		Assert.isTrue(a.getDraftMode());
		this.AuditService.delete(auditId);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Audit a, final BindingResult binding) {
		
		ModelAndView res;
		Audit reconstructed;
		
		reconstructed = this.AuditService.reconstruct(a, binding);
		final Collection<Position> pos = this.positionService.findFreePositions();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(a);
			res.addObject("positions", pos);
		} else
			try {
				this.AuditService.save(reconstructed);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				res = this.createEditModelAndView(a, "audit.commit.error");
				res.addObject("positions", pos);
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Audit a) {
		ModelAndView res;

		res = this.createEditModelAndView(a, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Audit a, final String messageCode) {
		ModelAndView res;

		if (a.getId() == 0)
			res = new ModelAndView("audit/create");
		else
			res = new ModelAndView("audit/edit");
		
		res.addObject("audit", a);
		res.addObject("message", messageCode);

		return res;
	}

}
