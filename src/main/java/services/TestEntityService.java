
//package services;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//
//import org.apache.commons.lang.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Validator;
//
//import repositories.TestEntityRepository;
//import domain.Actor;
//import domain.Auditor;
//import domain.TestEntity;
//
//
//@Service
//@Transactional
//public class TestEntityService {
//
//	// Managed repository -------------------------------------------
//	@Autowired
//	private TestEntityRepository	TestEntityRepository;
//
//	// Supported services -------------------------------------------
//
//	@Autowired
//	private ActorService	        actorService;
//
//	@Autowired
//	private RelationEntity1Service	relation1Service;
//	
//	@Autowired
//	private AuditorService	        auditorService;
//
//	@Autowired(required = false)
//	@Qualifier("validator")
//	private Validator		validator;
//
//
//	// Constructor methods -------------------------------------------
//	public TestEntityService() {
//		super();
//	}
//
//	// Simple CRUD methods ------------------------------------------
//
//	public TestEntity create() {
//		TestEntity res;
//		Actor principal;
//
//		// Principal must be an TestEntityor
//		principal = this.actorService.findByPrincipal();
//		Assert.isInstanceOf(RelationEntity1.class, principal);
//		final RelationEntity1 principalRelationEntity1 = this.relation1Service.findByPrincipal();
//
//		res = new TestEntity();
//
//		res.setBody("");
//		res.setPhoto("");
//		res.setMoment(new Date());
//		res.setTicker(this.generateTicker());
//		res.setDraftMode(true);
//		res.setRelation1(principalRelationEntity1);
//
//		return res;
//	}
//
//	
//	public TestEntity findOne(final int id) {
//		final TestEntity result = this.TestEntityRepository.findOne(id);
//
//		Assert.notNull(result);
//
//		return result;
//	}
//
//	public Collection<TestEntity> findAll() {
//		final Collection<TestEntity> result = this.TestEntityRepository.findAll();
//
//		return result;
//
//	}
//
//	public TestEntity save(final TestEntity a) {
//		Assert.notNull(a);
//		Actor principal;
//
//		// Principal must be a Company
//		principal = this.actorService.findByPrincipal();
//		Assert.isInstanceOf(RelationEntity1.class, principal);
//		//Another company can't edit other company's problems
//		final RelationEntity1 r1 = this.relation1Service.findByPrincipal();
//		Assert.isTrue(a.getRelation1().equals(r1));
//
//		this.checkTestEntity(a);
//		
//		if (a.getDraftMode() == true) {
//		a.setMoment(null);
//		}else if(a.getDraftMode() == false){
//			a.setMoment(new Date());
//		}
//
//		return this.TestEntityRepository.save(a);
//	}
//	
//
//	public void delete(final int TestEntityId) {
//		
//		Assert.notNull(TestEntityId);
//
//		final TestEntity a = this.findOne(TestEntityId);
//		Assert.notNull(a);
//
//		// Principal must be a Company
//		final Actor principal = this.actorService.findByPrincipal();
//		Assert.isInstanceOf(RelationEntity1.class, principal);
//		final RelationEntitiy1 r1 = this.relation1Service.findByPrincipal();
//		//Another company can't delete other company's problems
//		Assert.isTrue(a.getRelation1().equals(r1));
//		//TestEntity must be in draft mode
//		Assert.isTrue(a.getDraftMode());
//
//		this.TestEntityRepository.delete(TestEntityId);
//
//	}
//
//	public TestEntity reconstruct(final TestEntity a, final BindingResult binding) {
//		TestEntity res = new TestEntity();
//
//		if (a.getId() == 0)
//			res = a;
//		else {
//			final TestEntity copy = this.findOne(a.getId());
//
//			res.setTicker(a.getTicker());
//			res.setBody(a.getBody());
//			res.setPhoto(a.getPhoto());
//			res.setDraftMode(a.getDraftMode());
//			res.setMoment(a.getMoment());
//
//			res.setId(copy.getId());
//			res.setVersion(copy.getVersion());
//			res.setRelation1(copy.getRelation1());
//			res.setRelation2(copy.getRelation2());
//		}
//
//		this.validator.validate(res, binding);
//		return res;
//	}
//
//	// Other business methods ---------------------------------------------------------
//	
//	public String generateTicker() {
//		String ticker = "";
//		final DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
//		final Date date = new Date();
//		final String tickerDate = (dateFormat.format(date));
//		final String tickerAlphanumeric = RandomStringUtils.randomAlphanumeric(5).toUpperCase();
//		ticker = ticker.concat(tickerDate).concat("-").concat(tickerAlphanumeric);
//		return ticker;
//	}
//
//	public void flush() {
//		this.TestEntityRepository.flush();
//	}
//
//	public Collection<TestEntity> getTestEntitysByAntiquity (final int auditId,final int months) {
//		final Collection<TestEntity> result = new ArrayList<TestEntity>();
//
//		final Actor act = this.actorService.findByPrincipal();
//		Assert.isInstanceOf(Auditor.class, act);
//		final Auditor aud = this.auditorService.findByPrincipal();
//
//		Assert.notNull(aud);
//
//		final Collection<TestEntity> tests = this.getTestEntitysByRelationEntity2(auditId);
//
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
//		
//		Calendar c2 = Calendar.getInstance();
//		c2.setTime(new Date());
//
//		for (final TestEntity t : tests)
//			
//			if(months == 1 && t.getDraftMode() == false){
//				c.add(Calendar.MONTH, -1);
//				if (t.getMoment().after(c.getTime()))
//					result.add(t);
//			}else if(months == 2 && t.getDraftMode() == false){
//				c.add(Calendar.MONTH, -2);
//				c2.add(Calendar.MONTH, -1);
//				if (t.getMoment().after(c.getTime()) && t.getMoment().before(c2.getTime()) )
//					result.add(t);
//			}else if(months == 3 && t.getDraftMode() == false){
//				c.add(Calendar.MONTH, -2);
//				if (t.getMoment().before(c.getTime()))
//					result.add(t);
//			}
//	
//		return result;
//	}
//	
//	public void checkTestEntity(final TestEntity a) {
//		boolean check = true;
//
//		if (a.getBody() == null || a.getTicker() == null )
//			check = false;
//
//		Assert.isTrue(check);
//	}
//
//	public Collection<TestEntity> getTestEntitysByRelationEntity1 (final int relation1Id) {
//		final Actor logueado = this.actorService.findByPrincipal();
//		Assert.isInstanceOf(RelationEntity1.class, logueado);
//		return this.TestEntityRepository.getTestEntitysByRelationEntity1(relation1Id);
//	}
//
//	public Collection<TestEntity> getTestEntitysByRelationEntity2 (final int relation2Id) {
//		//    	Actor logueado = this.actorService.findByPrincipal();
//		//    	Assert.isInstanceOf(TestEntityor.class, logueado);
//		return this.TestEntityRepository.getTestEntitysByRelationEntity2(relation2Id);
//	}
//	
//
//}
