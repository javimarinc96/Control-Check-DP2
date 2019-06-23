
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.Actor;
import domain.Company;
import domain.CreditCard;
import domain.Curricula;
import domain.EducationData;
import domain.Rookie;
import domain.MiscellaneousData;
import domain.PersonalData;
import domain.PositionData;
import domain.SocialProfile;

@Service
@Transactional
public class ActorService {

	// Manage Repository
	@Autowired
	private ActorRepository	actorRepository;

	// Supported Services
	@Autowired
	private CompanyService	companyService;

	@Autowired
	private RookieService	rookieService;


	// CRUD methods

	public Actor findOne(final int actorId) {
		final Actor result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Actor> findAll() {
		final Collection<Actor> result = this.actorRepository.findAll();
		Assert.notNull(result);
		Assert.notEmpty(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		final Actor result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);

		this.actorRepository.delete(actor);
	}

	// Other business methods
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Actor findOneByUsername(final String username) {
		Assert.notNull(username);

		return this.actorRepository.findByUserName(username);
	}

	public Actor findActorBySocialProfile(final int socialProfileId) {
		return this.actorRepository.findActorBySocialProfileId(socialProfileId);
	}

	public void deleteActor() {
		final Actor principal = this.findByPrincipal();

		final Collection<Authority> authorities = principal.getUserAccount().getAuthorities();

		final Collection<SocialProfile> sProfiles = principal.getSocialProfiles();

		final CreditCard stock = new CreditCard();
		stock.setHolderName("Lorem ipsum");
		stock.setBrandName("VISA");
		stock.setCvv(111);
		stock.setExpiryMonth(12);
		stock.setExpiryYear(99);
		stock.setNumber("1111222233334444");

		principal.setName("Lorem ipsum");
		principal.setSurname("Lorem ipsum");
		principal.setVat("Lorem ipsum");
		principal.setCreditCard(stock);
		principal.setPhoto("Lorem ipsum");
		principal.setEmail("loremipsum@loremipsum.com");
		principal.setPhoneNumber("666666666");
		principal.setAddress("Lorem ipsum");

		if (sProfiles.isEmpty() == false)
			for (final SocialProfile s : sProfiles) {
				s.setSocialNetworkName("Lorem ipsum");
				s.setLink("https://www.loremipsum.com");
				s.setNick("Lorem ipsum");
			}

		if (authorities.contains(Authority.COMPANY)) {
			final Company company = this.companyService.findOne(principal.getId());
			company.setCommercialName("Lorem ipsum");
		} else if (authorities.contains(Authority.ROOKIE)) {
			final Rookie rookie = this.rookieService.findOne(principal.getId());
			final Collection<Curricula> curricula = rookie.getCurriculas();

			for (final Curricula c : curricula) {
				c.setPersonalData(new PersonalData());
				c.setEducationsData(new ArrayList<EducationData>());
				c.setMiscellaneousData(new MiscellaneousData());
				c.setPositionsData(new ArrayList<PositionData>());
			}

		}

		// Cambiamos la contraseña para que no se pueda acceder.
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hashedPassword = encoder.encodePassword("1234", null);
		principal.getUserAccount().setPassword(hashedPassword);
	}

	public String exportPersonalData() {
		final Actor principal = this.findByPrincipal();

		final Gson gson = new GsonBuilder().create();

		final String result = gson.toJson(principal);

		return result;
	}
}
