
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionDataRepository;
import domain.Actor;
import domain.Curricula;
import domain.Rookie;
import domain.PositionData;

@Service
@Transactional
public class PositionDataService {

	@Autowired
	private PositionDataRepository	positionDataRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private CurriculaService		curriculaService;


	public PositionDataService() {
		super();
	}

	public PositionData create(final Integer curriculumId) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final PositionData res = new PositionData();
		Assert.notNull(res);

		return res;
	}

	public PositionData findOne(final int positionDataId) {
		Assert.isTrue(positionDataId > 0);

		final PositionData res = this.positionDataRepository.findOne(positionDataId);

		Assert.notNull(res);

		return res;
	}

	public PositionData save(final PositionData positionData, final int curriculumId) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		this.checkPositionData(positionData);
		final Curricula curriculum = this.curriculaService.findOneToEdit(curriculumId);
		final PositionData res = this.positionDataRepository.save(positionData);
		final Collection<PositionData> positionsData = curriculum.getPositionsData();
		positionsData.add(res);
		curriculum.setPositionsData(positionsData);
		this.curriculaService.save(curriculum);

		return res;
	}

	public void delete(final PositionData positionData) {
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		Assert.notNull(positionData);
		Assert.isTrue(positionData.getId() > 0);

		final Curricula curriculum = this.curriculaService.findCurriculaByPositionDataId(positionData.getId());
		final Collection<PositionData> positionsDataCopia = curriculum.getPositionsData();
		positionsDataCopia.remove(positionData);
		curriculum.setPositionsData(positionsDataCopia);
		this.curriculaService.save(curriculum);

		this.positionDataRepository.delete(positionData);
	}

	public Collection<PositionData> findAll() {

		Collection<PositionData> result;

		result = this.positionDataRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public void checkPositionData(final PositionData positionData) {
		Boolean res = true;

		if (positionData.getDescription() == null || positionData.getEndDate() == null || positionData.getStartDate() == null || positionData.getTitle() == null)
			res = false;

		Assert.isTrue(res);
	}

	public PositionData findOneToEdit(final int id) {
		Rookie principal;

		// Principal must be a Company
		principal = this.rookieService.findByPrincipal();
		Assert.isInstanceOf(Rookie.class, principal);

		final PositionData result = this.positionDataRepository.findOne(id);

		final Curricula curricula = this.curriculaService.findCurriculaByPositionDataId(id);

		final Rookie rookie = this.rookieService.findRookieByCurriculaId(curricula.getId());
		Assert.notNull(result);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(rookie));

		return result;

	}

	public PositionData saveEdit(final PositionData positionData) {
		return this.positionDataRepository.save(positionData);
	}
}
