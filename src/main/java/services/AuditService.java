
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuditRepository;
import domain.Actor;
import domain.Audit;
import domain.Auditor;

@Service
@Transactional
public class AuditService {

	// Managed repository -------------------------------------------
	@Autowired
	private AuditRepository	auditRepository;

	// Supported services -------------------------------------------

	@Autowired
	private ActorService	actorService;

	@Autowired
	private AuditorService	auditorService;

	@Autowired(required = false)
	@Qualifier("validator")
	private Validator		validator;


	// Constructor methods -------------------------------------------
	public AuditService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Audit create() {
		Audit res;
		Actor principal;

		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Auditor.class, principal);
		final Auditor principalAuditor = this.auditorService.findByPrincipal();

		res = new Audit();

		res.setText("");
		res.setMoment(new Date());
		res.setScore(0.0);
		res.setDraftMode(true);
		res.setAuditor(principalAuditor);

		return res;
	}

	public Audit findOne(final int id) {
		final Audit result = this.auditRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Audit> findAll() {
		final Collection<Audit> result = this.auditRepository.findAll();

		return result;

	}

	public Audit save(final Audit a) {
		Assert.notNull(a);
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Auditor.class, principal);
		//Another company can't edit other company's problems
		final Auditor aud = this.auditorService.findByPrincipal();
		Assert.isTrue(a.getAuditor().equals(aud));

		this.checkAudit(a);

		return this.auditRepository.save(a);
	}

	public void delete(final int auditId) {
		Assert.notNull(auditId);

		final Audit a = this.findOne(auditId);
		Assert.notNull(a);

		// Principal must be a Company
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Auditor.class, principal);
		final Auditor aud = this.auditorService.findByPrincipal();
		//Another company can't delete other company's problems
		Assert.isTrue(a.getAuditor().equals(aud));
		//Audit must be in draft mode
		Assert.isTrue(a.getDraftMode());

		this.auditRepository.delete(auditId);

	}

	public Audit reconstruct(final Audit a, final BindingResult binding) {
		Audit res = new Audit();

		if (a.getId() == 0)
			res = a;
		else {
			final Audit copy = this.findOne(a.getId());

			res.setText(a.getText());
			res.setScore(a.getScore());
			res.setDraftMode(a.getDraftMode());
			res.setMoment(a.getMoment());

			res.setId(copy.getId());
			res.setVersion(copy.getVersion());
			res.setAuditor(copy.getAuditor());
			res.setPosition(copy.getPosition());
		}

		this.validator.validate(res, binding);
		return res;
	}

	// Other business methods ---------------------------------------------------------

	public void flush() {
		this.auditRepository.flush();
	}

	public void checkAudit(final Audit a) {
		boolean check = true;

		if (a.getText() == null || a.getMoment() == null || a.getDraftMode() == null || a.getScore() == null)
			check = false;

		Assert.isTrue(check);
	}

	public Collection<Audit> getAuditsByAuditor(final int auditorId) {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Auditor.class, logueado);
		return this.auditRepository.getAuditsByAuditor(auditorId);
	}

	public Collection<Audit> getAuditsByPosition(final int positionId) {
		//    	Actor logueado = this.actorService.findByPrincipal();
		//    	Assert.isInstanceOf(Auditor.class, logueado);
		return this.auditRepository.getAuditsByPosition(positionId);
	}

}
