
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaneousDataRepository;
import domain.Actor;
import domain.Curricula;
import domain.Rookie;
import domain.MiscellaneousData;

@Service
@Transactional
public class MiscellaneousDataService {

	@Autowired
	private MiscellaneousDataRepository	miscellaneousDataRepository;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private RookieService				rookieService;

	@Autowired
	private CurriculaService			curriculaService;


	public MiscellaneousDataService() {
		super();
	}

	public MiscellaneousData create(final Integer curriculumId) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final MiscellaneousData res = new MiscellaneousData();
		final Curricula curriculum = this.curriculaService.findOne(curriculumId);
		curriculum.setMiscellaneousData(res);
		this.curriculaService.save(curriculum);
		Assert.notNull(res);

		return res;
	}

	public MiscellaneousData findOne(final int miscellaneousDataId) {
		Assert.isTrue(miscellaneousDataId > 0);

		final MiscellaneousData res = this.miscellaneousDataRepository.findOne(miscellaneousDataId);

		Assert.notNull(res);

		return res;
	}

	public MiscellaneousData save(final MiscellaneousData miscellaneousData) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		this.checkMiscellaneousData(miscellaneousData);

		final MiscellaneousData res = this.miscellaneousDataRepository.save(miscellaneousData);

		return res;
	}

	public void delete(final MiscellaneousData miscellaneousData) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		Assert.notNull(miscellaneousData);
		Assert.isTrue(miscellaneousData.getId() > 0);

		this.miscellaneousDataRepository.delete(miscellaneousData);
	}

	public Collection<MiscellaneousData> findAll() {

		Collection<MiscellaneousData> result;

		result = this.miscellaneousDataRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public void checkMiscellaneousData(final MiscellaneousData miscellaneousData) {
		Boolean res = true;

		if (miscellaneousData.getAttachments() == null || miscellaneousData.getFreeText() == null)
			res = false;

		Assert.isTrue(res);
	}

	public MiscellaneousData findOneToEdit(final int id) {
		Rookie principal;

		// Principal must be a Company
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final MiscellaneousData result = this.miscellaneousDataRepository.findOne(id);

		final Curricula curricula = this.curriculaService.findCurriculaByMiscellaneousDataId(id);

		final Rookie rookie = this.rookieService.findRookieByCurriculaId(curricula.getId());
		Assert.notNull(result);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(rookie));

		return result;

	}

}
