
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import domain.Actor;
import domain.Company;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class PositionService {

	// Managed repository -------------------------------------------
	@Autowired
	private PositionRepository	positionRepository;

	// Supported services -------------------------------------------
	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ProblemService		problemService;


	// Constructor methods -------------------------------------------
	public PositionService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Position create() {
		Position result;
		Actor principal;

		result = new Position();

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);

		final Company c = this.companyService.findByPrincipal();
		result.setTicker(this.generateTicker(c.getCommercialName()));

		result.setCompany(c);

		return result;
	}

	public Position findOne(final int id) {
		final Position result = this.positionRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Position> findAll() {
		final Collection<Position> result = this.positionRepository.findAll();

		return result;
	}

	public Collection<Position> getPositionByCompany() {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, logueado);
		return this.positionRepository.findAllPositionsByCompany(logueado.getId());
	}

	public Position save(final Position position) {
		Assert.notNull(position);
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);

		this.checkPosition(position);

		if (position.getId() != 0)
			Assert.isTrue(principal.equals(position.getCompany()));

		return this.positionRepository.save(position);
	}

	public void delete(final int positionId) {

		Actor principal;

		//No puede tener problemas asignados para ser borrado
		Assert.isTrue(this.isDeleteable(positionId));

		final Position p = this.findOne(positionId);

		Assert.notNull(p);

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);

		Assert.isTrue(principal.equals(p.getCompany()));

		Assert.isTrue(p.getDraftMode());

		this.positionRepository.delete(p);

	}
	// Other business methods ---------------------------------------------------------

	public void flush() {
		this.positionRepository.flush();
	}

	public void checkPosition(final Position position) {
		boolean check = true;
		final Date now = new Date();

		if (position.getTitle() == null || position.getDescription() == null || position.getDeadline() == null || position.getProfileRequired() == null || position.getSkillsRequired() == null || position.getTechnologyRequired() == null)
			check = false;

		if (position.getDeadline().before(now))
			check = false;

		if (position.getDraftMode() == false) {
			// Comprobamos que la position tenga al menos 2 problems asociados.
			final Collection<Problem> problems = this.problemService.getProblemsByPosition(position.getId());
			Assert.isTrue(problems != null && problems.size() >= 2);
		}

		Assert.isTrue(check);
	}

	public String generateTicker(final String company) {
		String result = "";

		if (company.length() <= 3)
			result = String.format("%4s", company).replace(' ', 'X');
		else
			result = company.substring(0, 4);

		result = result.concat("-");

		final Random r = new Random();

		final String n = String.format("%04d", r.nextInt(10000));

		result = result.concat(n);

		return result;
	}


	public boolean isDeleteable(final int positionId) {
		boolean res = false;

		final Position p = this.findOne(positionId);

		Assert.notNull(p);

		final Collection<Problem> problems = this.problemService.getProblemsByPosition(positionId);

		if ((problems == null || problems.size() == 0) && p.getDraftMode())
			res = true;

		return res;
	}

	public Position findOneToEdit(final int id) {
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);

		final Position result = this.positionRepository.findOne(id);

		Assert.notNull(result);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(result.getCompany()));

		Assert.isTrue(result.getDraftMode());

		return result;

	}

	public Collection<Position> searcherPositions(final String searcher) {
		final Collection<Position> result = new ArrayList<Position>();
		final Collection<Position> all = this.findAll();

		final String s = searcher.toLowerCase();

		for (final Position p : all)
			if (p.getTitle().toLowerCase().contains(s) || p.getDescription().toLowerCase().contains(s) || p.getProfileRequired().toLowerCase().contains(s) || p.getSkillsRequired().toLowerCase().contains(s)
				|| p.getTechnologyRequired().toLowerCase().contains(s) || p.getCompany().getCommercialName().toLowerCase().contains(s) || p.getTicker().toLowerCase().contains(s))
				result.add(p);

		return result;
	}

	public Collection<Problem> findAllProblemsByPosition(final int positionId) {
		return this.positionRepository.findAllProblemsByPosition(positionId);
	}
	
	public Collection<Position> findFreePositions() {
		return this.positionRepository.findFreePositions();
	}
	
	public Collection<Position> findAllPositionsByCompany(final int companyId) {
		// Principal must be a Company
	    return this.positionRepository.findAllPositionsByCompany(companyId);
	}
	


	
}
