
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

import repositories.RookieRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.CreditCard;
import domain.Curricula;
import domain.Finder;
import domain.Rookie;
import domain.SocialProfile;
import forms.RookieForm;

@Service
@Transactional
public class RookieService {

	// Manage Repository
	@Autowired
	private RookieRepository		rookieRepository;

	@Autowired(required = false)
	private Validator				validator;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private FolderService			folderService;


	// CRUD methods

	public Rookie create() {
		final Rookie result = new Rookie();
		final CreditCard creditCard = new CreditCard();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<Curricula> curricula = new ArrayList<Curricula>();
		final Finder finder = new Finder();
		final Date now = new Date(System.currentTimeMillis() - 1000);
		finder.setMoment(now);
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

		authority.setAuthority(Authority.ROOKIE);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setFinder(finder);
		result.setCurriculas(curricula);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setSocialProfiles(socialProfiles);

		return result;

	}

	public Rookie save(final Rookie rookie) {
		Rookie res;
		Assert.notNull(rookie);
		Assert.isTrue(this.checkDate(rookie.getCreditCard().getExpiryMonth(), rookie.getCreditCard().getExpiryYear()));

		if (rookie.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = rookie.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);
			final UserAccount ua = this.userAccountRepository.save(userAccount);
			rookie.setUserAccount(ua);
		}
		res = this.rookieRepository.save(rookie);

		this.folderService.generateFolders(res.getId());

		return res;

	}

	public Rookie findOne(final int rookieId) {
		final Rookie result = this.rookieRepository.findOne(rookieId);
		Assert.notNull(result);

		return result;
	}

	public Rookie findOneNoAssert(final int rookieId) {
		final Rookie result = this.rookieRepository.findOne(rookieId);

		return result;
	}

	public Collection<Rookie> findAll() {
		final Collection<Rookie> result = this.rookieRepository.findAll();
		Assert.notNull(result);
		Assert.notEmpty(result);

		return result;
	}

	public void delete(final Rookie rookie) {
		Assert.notNull(rookie);

		this.rookieRepository.delete(rookie);
	}

	// Other business methods
	public Rookie findByPrincipal() {
		Rookie result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	/*** Reconstruct object, check validity and update binding ***/
	public Rookie reconstruct(final RookieForm rookieForm, final BindingResult binding) {
		final Rookie result = this.create();

		result.getCreditCard().setBrandName(rookieForm.getCreditCard().getBrandName());
		result.getCreditCard().setCvv(rookieForm.getCreditCard().getCvv());
		result.getCreditCard().setExpiryMonth(rookieForm.getCreditCard().getExpiryMonth());
		result.getCreditCard().setExpiryYear(rookieForm.getCreditCard().getExpiryYear());
		result.getCreditCard().setHolderName(rookieForm.getCreditCard().getHolderName());
		result.getCreditCard().setNumber(rookieForm.getCreditCard().getNumber());
		result.getUserAccount().setPassword(rookieForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(rookieForm.getUserAccount().getUsername());
		//		Assert.isTrue(rookieForm.getUserAccount().getPassword() == rookieForm.getConfirmPassword());

		result.setAddress(rookieForm.getAddress());
		result.setEmail(rookieForm.getEmail());
		result.setName(rookieForm.getName());
		result.setPhoneNumber(rookieForm.getPhoneNumber());
		result.setPhoto(rookieForm.getPhoto());
		result.setSurname(rookieForm.getSurname());
		result.setVat(rookieForm.getVat());
		result.setIsBanned(false);

		result.setFinder(rookieForm.getFinder());

		this.validator.validate(result, binding);

		return result;
	}

	public Rookie reconstruct(final Rookie rookie, final BindingResult binding) {
		Rookie result = new Rookie();
		final Rookie res = new Rookie();

		if (rookie.getId() == 0)
			result = rookie;
		else {
			result = this.findOne(rookie.getId());
			final CreditCard creditCard = rookie.getCreditCard();
			res.setAddress(rookie.getAddress());
			res.setEmail(rookie.getEmail());
			res.setName(rookie.getName());
			res.setPhoneNumber(rookie.getPhoneNumber());
			res.setPhoto(rookie.getPhoto());
			res.setSurname(rookie.getSurname());
			res.setId(result.getId());
			res.setVersion(result.getVersion());
			res.setUserAccount(result.getUserAccount());
			res.setCurriculas(result.getCurriculas());
			res.setFinder(result.getFinder());
			res.setSocialProfiles(result.getSocialProfiles());

			creditCard.setBrandName(rookie.getCreditCard().getBrandName());
			creditCard.setCvv(rookie.getCreditCard().getCvv());
			creditCard.setExpiryMonth(rookie.getCreditCard().getExpiryMonth());
			creditCard.setExpiryYear(rookie.getCreditCard().getExpiryYear());
			creditCard.setHolderName(rookie.getCreditCard().getHolderName());
			creditCard.setNumber(rookie.getCreditCard().getNumber());
			res.setCreditCard(creditCard);
			res.setVat(rookie.getVat());
		}
		this.validator.validate(res, binding);

		return res;
	}
	/************************************************************************************************/

	public Rookie findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Rookie result;

		result = this.rookieRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkIfRookie() {
		boolean res = false;

		Collection<Authority> authority;
		authority = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authority)
			if (a.getAuthority().equals(Authority.ROOKIE))
				res = true;
		Assert.isTrue(res);
	}

	public Rookie findOneByUsername(final String username) {
		Assert.notNull(username);

		return this.rookieRepository.findByUserName(username);
	}

	//	public Rookie findRookieByApplicationId(final int applicationId) {
	//		return this.rookieRepository.findRookieByApplicationId(applicationId);
	//	}

	public void flush() {
		this.rookieRepository.flush();
	}

	public Rookie findRookieByCurriculaId(final int curriculaId) {
		return this.rookieRepository.findRookieByCurriculaId(curriculaId);
	}

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
}
