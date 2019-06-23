
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Configurations;
import domain.Finder;
import domain.Position;

@Service
@Transactional
public class FinderService {

	// Managed repository -------------------------------------------
	@Autowired
	private FinderRepository		finderRepository;

	// Supported services -------------------------------------------
	@Autowired
	private ActorService			actorService;

	@Autowired
	private ConfigurationsService	configurationsService;


	// Constructor methods -------------------------------------------
	public FinderService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------
	public Finder save(final Finder f) {
		Assert.notNull(f);
		Finder result;
		result = this.finderRepository.save(f);
		return result;
	}

	public Finder findOne(final int finderId) {
		Assert.isTrue(finderId != 0);
		Finder result;
		result = this.finderRepository.findOne(finderId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;
		result = this.finderRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	//Other business methods --------------------------------------------
	public Collection<Position> finderResults(final Finder f) {
		final Collection<Position> result = new ArrayList<Position>();

		if (f.getKeyWord() != null) {
			final Collection<Position> temp1 = this.finderRepository.findPositionsByKeyWordTicker(f.getKeyWord());
			final Collection<Position> temp2 = this.finderRepository.findPositionsByKeyWordTitle(f.getKeyWord());
			final Collection<Position> temp3 = this.finderRepository.findPositionsByKeyWordDescription(f.getKeyWord());
			final Collection<Position> temp4 = this.finderRepository.findPositionsByKeyWordSkills(f.getKeyWord());
			final Collection<Position> temp5 = this.finderRepository.findPositionsByKeyWordTechnologies(f.getKeyWord());
			final Collection<Position> temp6 = this.finderRepository.findPositionsByKeyWordProfile(f.getKeyWord());

			final Collection<Position> temp = new ArrayList<Position>();

			for (final Position p : temp1)
				if (!temp.contains(p))
					temp.add(p);

			for (final Position p : temp2)
				if (!temp.contains(p))
					temp.add(p);

			for (final Position p : temp3)
				if (!temp.contains(p))
					temp.add(p);

			for (final Position p : temp4)
				if (!temp.contains(p))
					temp.add(p);

			for (final Position p : temp5)
				if (!temp.contains(p))
					temp.add(p);

			for (final Position p : temp6)
				if (!temp.contains(p))
					temp.add(p);

			if (temp.isEmpty()) {
				result.clear();
				return result;
			} else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getDeadline() != null) {
			final Collection<Position> temp = this.finderRepository.findPositionsByDeadline(f.getDeadline());

			if (temp.isEmpty()) {
				result.clear();
				return result;
			} else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getMaximumDeadline() != null) {
			final Collection<Position> temp = this.finderRepository.findPositionsByMaximumDeadline(f.getMaximumDeadline());
			if (temp.isEmpty()) {
				result.clear();
				return result;
			} else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getMinimumSalary() != null) {
			final Collection<Position> temp = this.finderRepository.findPositionsByMinimumSalary(f.getMinimumSalary());
			if (temp.isEmpty()) {
				result.clear();
				return result;
			} else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		return result;
	}

	public boolean finderUpdate(final Finder f) {
		boolean result = false;
		final Finder finder = this.finderRepository.findOne(f.getId());

		final Configurations c = this.configurationsService.getConfiguration();
		final int finderCache = c.getCacheTime();

		final Date cacheMoment = DateUtils.addHours(finder.getMoment(), finderCache);

		final Date now = new Date();

		if (now.after(cacheMoment))
			result = true;

		return result;
	}

}
