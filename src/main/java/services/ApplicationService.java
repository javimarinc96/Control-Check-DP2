
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Actor;
import domain.Application;
import domain.Company;
import domain.Rookie;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ApplicationService {

	// Managed repository -------------------------------------------
	@Autowired
	private ApplicationRepository	applicationRepository;

	// Supported services -------------------------------------------
	@Autowired
	private ActorService			actorService;

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private CompanyService			companyService;

	@Autowired
	private PositionService			positionService;


	// Constructor methods -------------------------------------------
	public ApplicationService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Application create() {
		Application result;
		Actor principal;

		result = new Application();

		// Principal must be a Rookie
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final Rookie h = this.rookieService.findByPrincipal();
		final Date create = new Date();

		result.setStatus("PENDING");
		result.setAnswerDescription("");
		result.setLinkCode("");
		result.setMomentSubmit(create);
		result.setRookie(h);

		final Collection<Position> positions = this.positionService.findAll();

		for (final Position p : positions)
			for (final Problem pr : this.positionService.findAllProblemsByPosition(p.getId()))
				if (result.getProblem() == null)
					result.setProblem(pr);

		return result;
	}

	public Application save(final Application application) {
		Assert.notNull(application);
		Actor principal;

		// Principal must be a Rookie
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final Collection<Problem> problems = this.positionService.findAllProblemsByPosition(application.getPosition().getId());

		final Problem problem = this.getRandomProblem(problems);
		application.setProblem(problem);

		final Date submit = new Date(System.currentTimeMillis() - 1000);
		application.setMomentSubmit(submit);

		this.checkApplication(application);

		if (application.getId() != 0)
			this.checkEmpties(application);

		if (application.getId() != 0 && application.getStatus().equals("PENDING"))
			application.setStatus("SUBMITTED");

		if (application.getId() != 0 && application.getRookie() == null)
			application.setRookie(this.rookieService.findByPrincipal());
		else
			Assert.isTrue(principal.equals(application.getRookie()));

		return this.applicationRepository.save(application);
	}
	public void delete(final int applicationId) {

		Actor principal;

		final Application a = this.findOne(applicationId);

		Assert.notNull(a);

		// Principal must be a Rookie
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		Assert.isTrue(principal.equals(a.getRookie()));

		this.applicationRepository.delete(a);

	}

	public Application findOne(final int id) {
		final Application result = this.applicationRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Application> findAll() {
		final Collection<Application> result = this.applicationRepository.findAll();

		return result;
	}

	public void acceptApplication(final int applicationId) {
		Assert.isTrue(applicationId != 0);
		final Application application = this.findOne(applicationId);
		Assert.notNull(application);
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);

		application.setStatus("APPROVED");
		this.applicationRepository.save(application);
	}

	public void rejectApplication(final int applicationId) {
		Assert.isTrue(applicationId != 0);
		final Application application = this.findOne(applicationId);
		Assert.notNull(application);
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);

		application.setStatus("REJECTED");
		this.applicationRepository.save(application);
	}

	// Other business methods ---------------------------------------------------------

	public Problem getRandomProblem(final Collection<Problem> problems) {
		final List<Problem> problemsList = new ArrayList<Problem>(problems);
		final Integer listSize = problemsList.size();
		final Random random = new Random();
		final Integer randomNumber = random.nextInt(listSize);
		final Problem result = problemsList.get(randomNumber);
		return result;
	}

	public Collection<Application> getApplicationsByRookie(final int rookieId) {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, logueado);
		return this.applicationRepository.getApplicationsByRookie(rookieId);
	}

	public Collection<Application> getApplicationsByCompany(final int companyId) {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, logueado);
		return this.applicationRepository.getApplicationsByCompany(companyId);
	}

	public Collection<Application> findApplicationsByStatusCompany(final String status) {
		final Collection<Application> result = new ArrayList<Application>();

		final Actor act = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, act);
		final Company c = this.companyService.findByPrincipal();

		Assert.notNull(c);

		final Collection<Application> apps = this.getApplicationsByCompany(c.getId());

		for (final Application a : apps)
			if (a.getStatus().equals(status))
				result.add(a);

		return result;
	}

	public Collection<Application> findApplicationsByStatusRookie(final String status) {
		final Collection<Application> result = new ArrayList<Application>();

		final Actor act = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, act);
		final Rookie h = this.rookieService.findByPrincipal();

		Assert.notNull(h);

		final Collection<Application> apps = this.getApplicationsByRookie(h.getId());

		for (final Application a : apps)
			if (a.getStatus().equals(status))
				result.add(a);

		return result;
	}

	public Application findOneToEdit(final int id) {
		Actor principal;

		// Principal must be a Rookie
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final Application result = this.applicationRepository.findOne(id);

		Assert.notNull(result);

		// Debe ser el mismo Rookie que al que pertenece la Position
		Assert.isTrue(principal.equals(result.getRookie()));

		return result;

	}

	public void flush() {
		this.applicationRepository.flush();
	}

	public void checkEmpties(final Application application) {
		boolean check = true;

		if (application.getAnswerDescription().isEmpty() || application.getAnswerDescription() == null || application.getLinkCode().isEmpty() || application.getLinkCode() == null)
			check = false;

		Assert.isTrue(check);
	}

	public void checkApplication(final Application application) {
		boolean check = true;

		if (application.getStatus() == null || application.getMomentSubmit() == null || application.getAnswerDescription() == null || application.getProblem() == null || application.getPosition() == null || application.getRookie() == null)
			check = false;

		Assert.isTrue(check);
	}

}
