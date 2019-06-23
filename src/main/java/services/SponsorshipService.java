
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Actor;
import domain.Position;
import domain.Provider;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	// Managed repository -------------------------------------------

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ProviderService			providerService;


	// Constructor methods -------------------------------------------
	public SponsorshipService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Sponsorship create() {
		Sponsorship result;
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Provider.class, principal);
		result = new Sponsorship();
		final Position position = new Position();
		final Provider provider = this.providerService.findOne(principal.getId());
		result.setProvider(provider);
		result.setPosition(position);
		return result;
	}

	public Sponsorship save(final Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		Sponsorship res = new Sponsorship();
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Provider.class, principal);

		if (res.getId() == 0)
			res = this.sponsorshipRepository.save(sponsorship);
		else
			res = this.findOneToEdit(sponsorship.getId());

		return res;
	}
	public void delete(final int sponsorshipId) {

		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Provider.class, principal);

		final Sponsorship a = this.findOneToEdit(sponsorshipId);

		Assert.notNull(a);

		this.sponsorshipRepository.delete(a);

	}

	public Sponsorship findOne(final int id) {
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Provider.class, principal);
		final Sponsorship result = this.sponsorshipRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Sponsorship> findAll() {
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Provider.class, principal);
		final Collection<Sponsorship> result = this.sponsorshipRepository.findAll();

		return result;
	}

	public Sponsorship findOneToEdit(final int id) {
		Actor principal;

		// Principal must be a Company
		principal = this.actorService.findByPrincipal();

		final Sponsorship result = this.sponsorshipRepository.findOne(id);
		Assert.notNull(result);

		final Actor actor = result.getProvider();
		Assert.notNull(actor);

		// Debe ser el mismo Company que al que pertenece la Position
		Assert.isTrue(principal.equals(actor));

		return result;

	}

	public Collection<Sponsorship> findSponsorshipsByProvider(final int providerId) {
		final Provider provider = this.providerService.findOne(providerId);

		final Collection<Sponsorship> res = this.findAll();
		final Collection<Sponsorship> sponsorshipsProvider = new ArrayList<Sponsorship>();

		for (final Sponsorship s : res)
			if (s.getProvider().getId() == providerId)
				sponsorshipsProvider.add(s);
		return sponsorshipsProvider;
	}

	public void flush() {
		this.sponsorshipRepository.flush();
	}

	public Sponsorship findRandomOneByPosition(final int positionId) {
		final List<Sponsorship> aux = (List<Sponsorship>) this.sponsorshipRepository.findByPosition(positionId);
		Sponsorship res = null;
		final int aleatorio = (int) (Math.random() * aux.size());

		if (aux.size() != 0)
			res = aux.get(aleatorio);
		else
			for (final Sponsorship s : this.sponsorshipRepository.findAll())
				res = s;
		return res;
	}

	public Collection<Sponsorship> findByPosition(final Integer positionId) {
		return this.sponsorshipRepository.findByPosition(positionId);
	}

}
