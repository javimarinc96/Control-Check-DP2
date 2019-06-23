
package services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CurriculaRepository;
import domain.Actor;
import domain.Curricula;
import domain.EducationData;
import domain.Rookie;
import domain.MiscellaneousData;
import domain.PersonalData;
import domain.PositionData;
import forms.CurriculaForm;

@Service
@Transactional
public class CurriculaService {

	//Managed repository --------------------------------------

	@Autowired
	private CurriculaRepository			curriculaRepository;

	//Supported Services -----------------------------------

	@Autowired(required = false)
	private Validator					validator;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private RookieService				rookieService;

	@Autowired
	private PersonalDataService			personalDataService;

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;


	// Contructor methods
	public CurriculaService() {
		super();
	}

	public Curricula create() {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);
		final Curricula res = new Curricula();

		final MiscellaneousData miscellaneousData = new MiscellaneousData();
		res.setMiscellaneousData(miscellaneousData);

		final Collection<EducationData> educationDatas = Collections.<EducationData> emptySet();
		res.setEducationsData(educationDatas);

		final PersonalData personalData = new PersonalData();
		res.setPersonalData(personalData);

		final Collection<PositionData> positionsData = Collections.<PositionData> emptySet();
		res.setPositionsData(positionsData);

		Assert.notNull(res);

		return res;
	}

	public Curricula findOne(final int curriculaId) {
		Assert.isTrue(curriculaId > 0);

		final Curricula res = this.curriculaRepository.findOne(curriculaId);

		Assert.notNull(res);

		return res;
	}

	public Curricula save(final Curricula curricula) {
		Rookie principal;
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		this.checkCurricula(curricula);
		final Curricula res = this.curriculaRepository.save(curricula);

		if (curricula.getId() == 0)
			principal.getCurriculas().add(res);

		this.rookieService.save(principal);
		return res;
	}

	public void delete(final Curricula curricula) {
		Rookie principal;
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		Assert.notNull(curricula);
		Assert.isTrue(curricula.getId() > 0);

		principal.getCurriculas().remove(curricula);
		curricula.getEducationsData().removeAll(curricula.getEducationsData());
		curricula.getPositionsData().removeAll(curricula.getPositionsData());
		this.miscellaneousDataService.delete(curricula.getMiscellaneousData());
		this.personalDataService.delete(curricula.getPersonalData());
		this.curriculaRepository.delete(curricula);
	}

	public Curricula reconstruct(final CurriculaForm form, final BindingResult binding) {
		final Curricula res = this.create();
		res.setMiscellaneousData(form.getMiscellaneousData());
		res.setPersonalData(form.getPersonalData());
		this.validator.validate(res, binding);
		return res;
	}

	public Collection<Curricula> findAll() {

		Collection<Curricula> result;

		result = this.curriculaRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	//Check Curricula
	public void checkCurricula(final Curricula curricula) {
		Boolean res = true;

		if (curricula.getEducationsData() == null || curricula.getMiscellaneousData() == null || curricula.getPersonalData() == null || curricula.getPositionsData() == null)
			res = false;

		Assert.isTrue(res);
	}

	public Curricula findOneToEdit(final int id) {
		Rookie principal;

		// Principal must be a Company
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final Curricula result = this.curriculaRepository.findOne(id);

		final Rookie rookie = this.rookieService.findRookieByCurriculaId(id);
		Assert.notNull(result);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(rookie));

		return result;

	}

	public Curricula findCurriculaByEducationDataId(final int educationDataId) {
		return this.curriculaRepository.findCurriculaByEducationDataId(educationDataId);
	}

	public Curricula findCurriculaByMiscellaneousDataId(final int miscellaneousDataId) {
		return this.curriculaRepository.findCurriculaByMiscellaneousDataId(miscellaneousDataId);
	}

	public Curricula findCurriculaByPositionDataId(final int positionDataId) {
		return this.curriculaRepository.findCurriculaByPositionDataId(positionDataId);
	}

	public Curricula findCurriculaByPersonalDataId(final int personalDataId) {
		return this.curriculaRepository.findCurriculaByPersonalDataId(personalDataId);
	}
}
