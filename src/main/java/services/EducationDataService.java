
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationDataRepository;
import domain.Actor;
import domain.Curricula;
import domain.EducationData;
import domain.Rookie;

@Service
@Transactional
public class EducationDataService {

	@Autowired
	private EducationDataRepository	educationDataRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private CurriculaService		curriculaService;


	public EducationDataService() {
		super();
	}

	public EducationData create(final Integer curriculumId) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final EducationData res = new EducationData();

		Assert.notNull(res);

		return res;
	}

	public EducationData findOne(final int educationDataId) {
		Assert.isTrue(educationDataId > 0);

		final EducationData res = this.educationDataRepository.findOne(educationDataId);

		Assert.notNull(res);

		return res;
	}

	public EducationData save(final EducationData educationData, final int curriculumId) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		this.checkEducationData(educationData);

		final EducationData res = this.educationDataRepository.save(educationData);

		final Curricula curriculum = this.curriculaService.findOne(curriculumId);
		final Collection<EducationData> educationsDataCopia = curriculum.getEducationsData();
		educationsDataCopia.add(res);
		curriculum.setEducationsData(educationsDataCopia);
		this.curriculaService.save(curriculum);

		return res;
	}

	public void delete(final EducationData educationData) {
		Assert.notNull(educationData);
		Assert.isTrue(educationData.getId() > 0);
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);
		final Curricula curriculum = this.curriculaService.findCurriculaByEducationDataId(educationData.getId());
		final Collection<EducationData> educationsDataCopia = curriculum.getEducationsData();
		educationsDataCopia.remove(educationData);
		curriculum.setEducationsData(educationsDataCopia);
		this.curriculaService.save(curriculum);

		this.educationDataRepository.delete(educationData);
	}

	public Collection<EducationData> findAll() {

		Collection<EducationData> result;

		result = this.educationDataRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public void checkEducationData(final EducationData educationData) {
		Boolean res = true;

		if (educationData.getDegree() == null || educationData.getEndDate() == null || educationData.getInstitution() == null || educationData.getMark() == null || educationData.getStartDate() == null)
			res = false;

		Assert.isTrue(res);
	}

	public EducationData findOneToEdit(final int id) {
		Rookie principal;

		// Principal must be a Company
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final EducationData result = this.educationDataRepository.findOne(id);

		final Curricula curricula = this.curriculaService.findCurriculaByEducationDataId(id);

		final Rookie rookie = this.rookieService.findRookieByCurriculaId(curricula.getId());
		Assert.notNull(result);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(rookie));

		return result;

	}

	public EducationData saveEdit(final EducationData educationData) {
		return this.educationDataRepository.save(educationData);
	}

}
