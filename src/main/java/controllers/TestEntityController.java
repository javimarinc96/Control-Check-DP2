
//package controllers;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import services.ActorService;
//import services.AuditorService;
//import services.RelationEntity1Service;
//import services.RelationEntity2Service;
//import services.TestEntityService;
//import controllers.AbstractController;
//import domain.Actor;
//import domain.RelationEntity1;
//import domain.RelationEntity2;
//import domain.TestEntity;
//
//@Controller
//@RequestMapping("/testEntity")
//public class TestEntityController extends AbstractController {
//
//	@Autowired
//	private TestEntityService	        TestEntityService;
//	@Autowired
//	private RelationEntity2Service		relation2Service;
//	@Autowired
//	private ActorService				actorService;
//	@Autowired
//	private AuditorService				auditorService;
//	@Autowired
//	private RelationEntity1Service	    relation1Service;
//
//
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//
//		ModelAndView res;
//		Collection<TestEntity> TestEntitys;
//
//		final int logueadoId = this.actorService.findByPrincipal().getId();
//		TestEntitys = this.TestEntityService.getTestEntitysByRelationEntity1(logueadoId);
//
//		res = new ModelAndView("testEntity/list");
//		res.addObject("testEntitys", TestEntitys);
//		res.addObject("requestURI", "testEntity/list.do");
//
//		return res;
//	}
//	
//	@RequestMapping(value = "/listAudit", method = RequestMethod.GET)
//	public ModelAndView listAudit(@RequestParam final int auditId) {
//
//		ModelAndView res;
//		Collection<TestEntity> OneMonthOlds;
//		Collection<TestEntity> TwoMonthOlds;
//		Collection<TestEntity> ThreeMonthOlds;
//
//		final int logueadoId = this.actorService.findByPrincipal().getId();
//		
//		OneMonthOlds = this.TestEntityService.getTestEntitysByAntiquity(auditId, 1);
//		TwoMonthOlds = this.TestEntityService.getTestEntitysByAntiquity(auditId, 2);
//		ThreeMonthOlds = this.TestEntityService.getTestEntitysByAntiquity(auditId, 3);
//
//		res = new ModelAndView("testEntity/listAudit");
//		res.addObject("oneMonthOlds", OneMonthOlds);
//		res.addObject("twoMonthOlds", TwoMonthOlds);
//		res.addObject("threeMonthOlds", ThreeMonthOlds);
//		res.addObject("requestURI", "testEntity/listAudit.do");
//
//		return res;
//	}
//	
//
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView res;
//		TestEntity pro;
//
//		final Collection<RelationEntity2> relation2s = this.relation2Service.findFreeRelationEntity2s();
//
//		pro = this.TestEntityService.create();
//		res = this.createEditModelAndView(pro);
//		res.addObject("relation2s", relation2s);
//
//		return res;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam final int testEntityId) {
//		ModelAndView res;
//		TestEntity a;
//
//		a = this.TestEntityService.findOne(testEntityId);
//		res = this.createEditModelAndView(a);
//
//		final Actor principal = this.actorService.findByPrincipal();
//		Assert.isInstanceOf(RelationEntity1.class, principal);
//		final RelationEntity1 r1 = this.relation1Service.findByPrincipal();
//
//		if (a.getDraftMode() == false || !a.getRelationEntity1().equals(r1))
//			res = new ModelAndView("redirect:list.do");
//
//		return res;
//	}
//
//	@RequestMapping(value = "/show", method = RequestMethod.GET)
//	public ModelAndView show(@RequestParam final int testEntityId) {
//		ModelAndView result;
//		TestEntity a;
//		a = this.TestEntityService.findOne(testEntityId);
//		result = new ModelAndView("testEntity/show");
//		result.addObject("testEntity", a);
//
//		return result;
//	}
//
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public ModelAndView delete(@RequestParam final int testEntityId) {
//
//		ModelAndView res;
//		TestEntity a;
//		a = this.TestEntityService.findOne(testEntityId);
//		this.TestEntityService.delete(testEntityId);
//		res = new ModelAndView("redirect:list.do");
//		return res;
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(TestEntity a, final BindingResult binding) {
//		
//		ModelAndView res;
//		TestEntity reconstructed;
//		
//		reconstructed = this.TestEntityService.reconstruct(a, binding);
//		final Collection<RelationEntity2> r2s = this.relation2Service.findFreeRelationEntity2s();
//
//		if (binding.hasErrors()) {
//			System.out.println(binding.getAllErrors());
//			res = this.createEditModelAndView(a);
//			res.addObject("relation2s", r2s);
//		} else
//			try {
//				this.TestEntityService.save(reconstructed);
//				res = new ModelAndView("redirect:list.do");
//			} catch (final Throwable oops) {
//				oops.printStackTrace();
//				res = this.createEditModelAndView(a, "testEntity.commit.error");
//				res.addObject("relation2s", r2s);
//			}
//
//		return res;
//	}
//
//	protected ModelAndView createEditModelAndView(final TestEntity a) {
//		ModelAndView res;
//
//		res = this.createEditModelAndView(a, null);
//
//		return res;
//	}
//
//	protected ModelAndView createEditModelAndView(final TestEntity a, final String messageCode) {
//		ModelAndView res;
//
//		if (a.getId() == 0)
//			res = new ModelAndView("testEntity/create");
//		else
//			res = new ModelAndView("testEntity/edit");
//		
//		res.addObject("testEntity", a);
//		res.addObject("message", messageCode);
//
//		return res;
//	}
//
//}
