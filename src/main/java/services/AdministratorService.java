
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Administrator;
import domain.CreditCard;
import domain.Message;
import domain.Provider;
import domain.SocialProfile;
import forms.AdministratorForm;

@Service
@Transactional
public class AdministratorService {

	@Autowired(required = false)
	private Validator				validator;

	// Manage Repository
	@Autowired
	private AdministratorRepository	adminRepository;

	// Supporting services
	@Autowired
	private ConfigurationsService	configurationsService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	/*************************************
	 * CRUD methods
	 ********************************/
	public Administrator create() {
		// Initialice
		final UserAccount userAccount = new UserAccount();
		final CreditCard creditCard = new CreditCard();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final Administrator admin = new Administrator();
		admin.setUserAccount(userAccount);
		admin.setCreditCard(creditCard);
		admin.setSocialProfiles(socialProfiles);

		return admin;
	}

	public Collection<Administrator> findAll() {
		final Collection<Administrator> result = this.adminRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(final int adminID) {
		final Administrator result = this.adminRepository.findOne(adminID);
		Assert.notNull(result);
		return result;
	}

	public Administrator save(final Administrator admin) {
		Assert.notNull(admin);
		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		Assert.isTrue(this.checkDate(admin.getCreditCard().getExpiryMonth(), admin.getCreditCard().getExpiryYear()));

		if (admin.getId() == 0) {

			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = admin.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);
			final UserAccount ua = this.userAccountRepository.save(userAccount);
			admin.setUserAccount(ua);

			if (!admin.getPhoneNumber().startsWith("+")) {
				final String countryCode = this.configurationsService.getConfiguration().getCountryCode();
				final String phoneNumer = admin.getPhoneNumber();
				admin.setPhoneNumber(countryCode.concat(phoneNumer));
			}
		} else {
			if (!admin.getPhoneNumber().startsWith("+")) {
				final String countryCode = this.configurationsService.getConfiguration().getCountryCode();
				final String phoneNumer = admin.getPhoneNumber();
				admin.setPhoneNumber(countryCode.concat(phoneNumer));
			}
			;
		}

		final Administrator result = this.adminRepository.save(admin);

		this.folderService.generateFolders(result.getId());

		return result;

	}

	public void delete(final Administrator admin) {
		Assert.notNull(admin);
		Assert.isTrue(admin.getId() != 0);
		this.adminRepository.delete(admin);
	}

	/*************************************
	 * Other business methods
	 ********************************/
	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrator result;

		result = this.adminRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	// 12.1 Create user accounts for new administrators---------------------------------------------------
	public Administrator registerNewAdmin(final Administrator admin) {
		Administrator principal;

		// Make sure that the principal is an Admin
		principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		// Check admin is not null
		Assert.notNull(admin);

		// Saves admin in the databese
		return this.adminRepository.save(admin);
	}

	// 12.2 Manage the catalogue of positions
	// ---------------------------------------------------

	// DASHBOARD -----------------

	public Double avgAuditScore() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.avgAuditScore();
	}

	public int maxAuditScore() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.maxAuditScore();
	}

	public int minAuditScore() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.minAuditScore();
	}

	public Double stddevAuditScore() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.stddevAuditScore();
	}

	public Double avgScoreCompany() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.getAverageScoreCompany();
	}

	public Double maxScoreCompany() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.getMaximumScoreCompany();
	}

	public double minScoreCompany() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.getMinimumScoreCompany();
	}

	public Double stddevScoreCompany() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.getStandardDeviationScoreCompany();
	}

	public Double avgResults() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.avgResults();
	}

	public Double avgItemsProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.avgItemsProvider();
	}

	public Double minItemsProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.minItemsProvider();
	}

	public Double maxItemsProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.maxItemsProvider();
	}

	public Double stddevItemsProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.stddevItemsProvider();
	}

	public int maxResults() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.maxResults();
	}

	public int minResults() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.minResults();
	}

	public Double stddevResults() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.stddevResults();
	}

	public Double ratioFinders() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.ratioFinders();
	}

	// 28.2 Spammers actors--------------------------------------------------------------------

	public Collection<Actor> getSpammers() {
		Actor principal;

		// Make sure that the principal is an Admin
		principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.adminRepository.findSpammers();
	}

	// 28.5 Ban an actor
	// ----------------------------------------------------------------

	public Actor ban(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getIsSpammer());
		Assert.isTrue(actor.getIsBanned() == false);

		// Make sure that the principal is an Admin
		final Object principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		actor.setIsBanned(true);

		return this.actorService.save(actor);

	}

	// 28.6 Unbans an actor, which means that his or her user account is
	// re-activated

	public Actor unban(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getIsBanned());

		// Make sure that the principal is an Admin
		final Object principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		actor.setIsBanned(false);

		return this.actorService.save(actor);

	}

	public void flush() {
		this.adminRepository.flush();
	}

	public Administrator reconstruct(final AdministratorForm administratorForm, final BindingResult binding) {
		final Administrator result = this.create();
		result.getUserAccount().setPassword(administratorForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(administratorForm.getUserAccount().getUsername());

		result.getCreditCard().setBrandName(administratorForm.getCreditCard().getBrandName());
		result.getCreditCard().setCvv(administratorForm.getCreditCard().getCvv());
		result.getCreditCard().setExpiryMonth(administratorForm.getCreditCard().getExpiryMonth());
		result.getCreditCard().setExpiryYear(administratorForm.getCreditCard().getExpiryYear());
		result.getCreditCard().setHolderName(administratorForm.getCreditCard().getHolderName());
		result.getCreditCard().setNumber(administratorForm.getCreditCard().getNumber());

		result.setAddress(administratorForm.getAddress());
		result.setEmail(administratorForm.getEmail());
		result.setName(administratorForm.getName());
		result.setPhoneNumber(administratorForm.getPhoneNumber());
		result.setPhoto(administratorForm.getPhoto());
		result.setSurname(administratorForm.getSurname());
		result.setVat(administratorForm.getVat());
		result.setIsBanned(false);

		this.validator.validate(result, binding);

		return result;
	}

	public Double maxPositions() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.maxPositions();
	}

	public Double avgPositions() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.avgPositions();
	}

	public Double minPositions() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.minPositions();
	}

	public Double stddevPositions() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.stddevPositions();
	}

	public Double avgAppRookie() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.avgAppRookie();
	}

	public Double maxAppRookie() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.maxAppRookie();
	}

	public Double minAppRookie() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.minAppRookie();
	}

	public Double stddevAppRookie() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.stddevAppRookie();
	}
	
	//LEVEL A----
	
	


	public Boolean checkDate(final int expiryMonth, final int expiryYear) {
		Boolean res = true;
		final Date date = new Date();
		final int month = date.getMonth();
		final int year = date.getYear() % 100;
		if (expiryYear == year)
			if (month > expiryMonth)
				res = false;

		return res;
	}
	
	public Double avgSponsorshipProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.avgSponsorshipProvider();
	}

	public Integer maxSponsorshipProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.maxSponsorshipProvider();
	}

	public Integer minSponsorshipProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.minSponsorshipProvider();
	}

	public Double stddevSponsorshipProvider() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.stddevSponsorshipProvider();
	}
	
	public Double avgSponsorshipPosition() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.avgSponsorshipPosition();
	}

	public Integer maxSponsorshipPosition() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.maxSponsorshipPosition();
	}

	public Integer minSponsorshipPosition() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.minSponsorshipPosition();
	}

	public Double stddevSponsorshipPosition() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.stddevSponsorshipPosition();
	}
	
	public Collection<Provider> aboveAvgSponsorships() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		return this.adminRepository.aboveAvgSponsorships();
	}

}
