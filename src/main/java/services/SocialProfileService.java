
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import domain.Actor;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	// Managed repository -------------------------------------------

	@Autowired
	private SocialProfileRepository	socialProfileRepository;

	@Autowired
	private ActorService			actorService;


	// Constructor methods -------------------------------------------
	public SocialProfileService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public SocialProfile create() {
		SocialProfile result;
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		result = new SocialProfile();
		return result;
	}

	public SocialProfile save(final SocialProfile socialProfile) {
		Assert.notNull(socialProfile);
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		if (socialProfile.getId() == 0) {
			principal.getSocialProfiles().add(socialProfile);
			this.actorService.save(principal);
		}

		return this.socialProfileRepository.save(socialProfile);
	}

	public void delete(final int socialProfileId) {

		Actor principal;

		final SocialProfile a = this.findOne(socialProfileId);

		Assert.notNull(a);

		// Principal must be a Rookie
		principal = this.actorService.findByPrincipal();
		principal.getSocialProfiles().remove(a);
		this.actorService.save(principal);
		this.socialProfileRepository.delete(a);

	}

	public SocialProfile findOne(final int id) {
		final SocialProfile result = this.socialProfileRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<SocialProfile> findAll() {
		final Collection<SocialProfile> result = this.socialProfileRepository.findAll();

		return result;
	}

	public SocialProfile findOneToEdit(final int id) {
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();

		final SocialProfile result = this.socialProfileRepository.findOne(id);
		Assert.notNull(result);

		final Actor actor = this.actorService.findActorBySocialProfile(id);
		Assert.notNull(actor);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(actor));

		return result;

	}

}
