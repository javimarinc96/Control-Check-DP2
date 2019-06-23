
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalDataRepository;
import domain.Actor;
import domain.Curricula;
import domain.Rookie;
import domain.PersonalData;

@Service
@Transactional
public class PersonalDataService {

	@Autowired
	private PersonalDataRepository	personalDataRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private CurriculaService		curriculaService;


	public PersonalDataService() {
		super();
	}

	public PersonalData create(final Integer curriculumId) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final PersonalData res = new PersonalData();
		final Curricula curriculum = this.curriculaService.findOne(curriculumId);
		curriculum.setPersonalData(res);
		this.curriculaService.save(curriculum);
		Assert.notNull(res);

		return res;
	}

	public PersonalData findOne(final int personalDataId) {
		Assert.isTrue(personalDataId > 0);

		final PersonalData res = this.personalDataRepository.findOne(personalDataId);

		Assert.notNull(res);

		return res;
	}

	public PersonalData save(final PersonalData personalData) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		this.checkPersonalData(personalData);

		final PersonalData res = this.personalDataRepository.save(personalData);

		return res;
	}

	public void delete(final PersonalData personalData) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		Assert.notNull(personalData);
		Assert.isTrue(personalData.getId() > 0);

		this.personalDataRepository.delete(personalData);
	}

	public Collection<PersonalData> findAll() {

		Collection<PersonalData> result;

		result = this.personalDataRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public void flush(){
		this.personalDataRepository.flush();
	}

	public void checkPersonalData(final PersonalData personalData) {
		Boolean res = true;

		if (personalData.getFullName() == null || personalData.getGitHubProfile() == null || personalData.getLinkedInProfile() == null || personalData.getPhoneNumber() == null || personalData.getStatement() == null)
			res = false;

		Assert.isTrue(res);
	}

	public PersonalData findOneToEdit(final int id) {
		Rookie principal;

		// Principal must be a Company
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final PersonalData result = this.personalDataRepository.findOne(id);

		final Curricula curricula = this.curriculaService.findCurriculaByPersonalDataId(id);

		final Rookie rookie = this.rookieService.findRookieByCurriculaId(curricula.getId());
		Assert.notNull(result);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(rookie));

		return result;

	}
}
