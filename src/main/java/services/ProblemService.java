
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ProblemRepository;
import domain.Actor;
import domain.Company;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	// Managed repository -------------------------------------------
	@Autowired
	private ProblemRepository	problemRepository;

	// Supported services -------------------------------------------

	@Autowired
	private ActorService		actorService;
	
	@Autowired
	private CompanyService		companyService;
	
	@Autowired(required = false)
	@Qualifier("validator")
	private Validator			validator;


	// Constructor methods -------------------------------------------
	public ProblemService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Problem create() {
		Problem res;
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);
		Company principalCompany = this.companyService.findByPrincipal();

		res = new Problem();

		res.setHint("");
		res.setTitle("");
		res.setAttachments("");
		res.setStatement("");
		res.setDraftMode(true);
	    res.setCompany(principalCompany);

		return res;
	}

	public Problem findOne(final int id) {
		final Problem result = this.problemRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Problem> findAll() {
		final Collection<Problem> result = this.problemRepository.findAll();
		return result;

    }
    
    public Collection<Problem> getProblemsByCompany(int companyId){
    	Actor logueado = this.actorService.findByPrincipal();
    	Assert.isInstanceOf(Company.class, logueado);
    	return this.problemRepository.getProblemsByCompany(companyId);
    }
    
 

	public Problem save(final Problem problem) {
		Assert.notNull(problem);
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);
		//Another company can't edit other company's problems
		Company c = this.companyService.findByPrincipal();
		Assert.isTrue(problem.getCompany().equals(c));
		
		this.checkproblem(problem);
		
		return this.problemRepository.save(problem);
	}

	public void delete(final int problemId) {
		Assert.notNull(problemId);
		
		Problem p = this.findOne(problemId);
		Assert.notNull(p);
		

		// Principal must be a Company
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, principal);
		Company c = this.companyService.findByPrincipal();
		//Another company can't delete other company's problems
		Assert.isTrue(p.getCompany().equals(c));
		//Problem must be in draft mode
		Assert.isTrue(p.getDraftMode());

		this.problemRepository.delete(problemId);

	}



	public Problem reconstruct(final Problem pro, final BindingResult binding) {
		Problem res = new Problem();

		if (pro.getId() == 0)
			res = pro;
		else {
			Problem copy = this.findOne(pro.getId());

			res.setTitle(pro.getTitle());
			res.setAttachments(pro.getAttachments());
			res.setDraftMode(pro.getDraftMode());
			res.setHint(pro.getHint());
			res.setStatement(pro.getStatement());
			
			
			res.setId(copy.getId());
			res.setVersion(copy.getVersion());
			res.setCompany(copy.getCompany());
			res.setPosition(copy.getPosition());
		}

		this.validator.validate(res, binding);
		return res;
	}

	// Other business methods ---------------------------------------------------------

	public void flush() {
		this.problemRepository.flush();
	}

	public void checkproblem(final Problem problem) {
		boolean check = true;

		if (problem.getTitle() == null || problem.getStatement() == null || problem.getHint() == null || problem.getAttachments() == null)
			check = false;

		Assert.isTrue(check);
	}

	public Collection<Problem> getProblemsByCompany() {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Company.class, logueado);
		Company principal = this.companyService.findByPrincipal();
		return this.problemRepository.getProblemsByCompany(principal.getId());
	}

	public Collection<Problem> getProblemsByPosition(int positionId){
		return this.problemRepository.getProblemsByPosition(positionId);
	}

}
