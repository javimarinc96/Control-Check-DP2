
package controllers.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CompanyService;
import services.PositionService;
import services.ProblemService;
import controllers.AbstractController;
import domain.Actor;
import domain.Company;
import domain.Position;
import domain.Problem;

@Controller
@RequestMapping("/problem/company")
public class ProblemCompanyController extends AbstractController {

	@Autowired
	private ProblemService	problemService;

	@Autowired
	private PositionService	positionService;
	@Autowired
	private ActorService	actorService;
	@Autowired
	private CompanyService	companyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Problem> problems;

		final int logueadoId = this.actorService.findByPrincipal().getId();
		problems = this.problemService.getProblemsByCompany(logueadoId);

		res = new ModelAndView("problem/list");
		res.addObject("problems", problems);
		res.addObject("requestURI", "problem/company/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Problem pro;

		final Collection<Position> positions = this.positionService.getPositionByCompany();

		pro = this.problemService.create();
		res = this.createEditModelAndView(pro);
		res.addObject("positions", positions);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int problemId) {
		ModelAndView res;
		Problem pro;

		pro = this.problemService.findOne(problemId);
		res = this.createEditModelAndView(pro);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);
		final Company c = this.companyService.findByPrincipal();

		if (pro.getDraftMode() == false || !pro.getCompany().equals(c))
			res = new ModelAndView("redirect:list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int problemId) {
		ModelAndView result;
		Problem pro;
		pro = this.problemService.findOne(problemId);
		result = new ModelAndView("problem/show");
		result.addObject("problem", pro);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int problemId) {

		ModelAndView res;
		Problem pro;
		pro = this.problemService.findOne(problemId);
		Assert.isTrue(pro.getDraftMode());
		this.problemService.delete(problemId);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Problem pro, final BindingResult binding) {
		
		ModelAndView res;
		Problem reconstructed;
		
		reconstructed = this.problemService.reconstruct(pro, binding);
		final Collection<Position> pos = this.positionService.getPositionByCompany();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(pro);
			res.addObject("positions", pos);
		} else
			try {
				this.problemService.save(reconstructed);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(pro, "problem.commit.error");
				res.addObject("positions", pos);
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Problem pro) {
		ModelAndView res;

		res = this.createEditModelAndView(pro, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Problem pro, final String messageCode) {
		ModelAndView res;

		if (pro.getId() == 0)
			res = new ModelAndView("problem/create");
		else
			res = new ModelAndView("problem/edit");
		res.addObject("problem", pro);
		res.addObject("message", messageCode);

		return res;
	}

}
