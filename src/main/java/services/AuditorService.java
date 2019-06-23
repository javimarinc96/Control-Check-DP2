
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

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Auditor;
import domain.CreditCard;
import domain.SocialProfile;
import forms.AuditorForm;

@Service
@Transactional
public class AuditorService {

	// Manage Repository
	@Autowired
	private AuditorRepository		auditorRepository;

	@Autowired(required = false)
	private Validator				validator;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private FolderService			folderService;


	// CRUD methods

	public Auditor create() {

		final Auditor result = new Auditor();

		final CreditCard creditCard = new CreditCard();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

		authority.setAuthority(Authority.AUDITOR);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setSocialProfiles(socialProfiles);

		return result;

	}

	public Auditor save(final Auditor auditor) {
		Auditor res;
		Assert.notNull(auditor);
		Assert.isTrue(this.checkDate(auditor.getCreditCard().getExpiryMonth(), auditor.getCreditCard().getExpiryYear()));

		if (auditor.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = auditor.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);

			final UserAccount ua = this.userAccountRepository.save(userAccount);
			auditor.setUserAccount(ua);

		}
		res = this.auditorRepository.save(auditor);

		this.folderService.generateFolders(res.getId());

		return res;

	}

	public Auditor findOne(final int AuditorId) {
		final Auditor result = this.auditorRepository.findOne(AuditorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Auditor> findAll() {
		final Collection<Auditor> result = this.auditorRepository.findAll();
		Assert.notNull(result);
		Assert.notEmpty(result);

		return result;
	}

	public void delete(final Auditor Auditor) {
		Assert.notNull(Auditor);

		this.auditorRepository.delete(Auditor);
	}

	// Other business methods
	public Auditor findByPrincipal() {
		Auditor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	/*** Reconstruct object, check validity and update binding ***/
	public Auditor reconstruct(final AuditorForm AuditorForm, final BindingResult binding) {
		final Auditor result = this.create();

		result.getUserAccount().setPassword(AuditorForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(AuditorForm.getUserAccount().getUsername());

		result.getCreditCard().setBrandName(AuditorForm.getCreditCard().getBrandName());
		result.getCreditCard().setCvv(AuditorForm.getCreditCard().getCvv());
		result.getCreditCard().setExpiryMonth(AuditorForm.getCreditCard().getExpiryMonth());
		result.getCreditCard().setExpiryYear(AuditorForm.getCreditCard().getExpiryYear());
		result.getCreditCard().setHolderName(AuditorForm.getCreditCard().getHolderName());
		result.getCreditCard().setNumber(AuditorForm.getCreditCard().getNumber());

		result.setAddress(AuditorForm.getAddress());
		result.setEmail(AuditorForm.getEmail());
		result.setName(AuditorForm.getName());
		result.setPhoneNumber(AuditorForm.getPhoneNumber());
		result.setPhoto(AuditorForm.getPhoto());
		result.setSurname(AuditorForm.getSurname());
		result.setVat(AuditorForm.getVat());
		result.setIsBanned(false);

		this.validator.validate(result, binding);

		return result;
	}

	public Auditor reconstruct(final Auditor Auditor, final BindingResult binding) {
		Auditor result = new Auditor();
		final Auditor res = new Auditor();

		if (Auditor.getId() == 0)
			result = Auditor;
		else {
			result = this.findOne(Auditor.getId());

			res.setAddress(Auditor.getAddress());
			res.setEmail(Auditor.getEmail());
			res.setName(Auditor.getName());
			res.setPhoneNumber(Auditor.getPhoneNumber());
			res.setPhoto(Auditor.getPhoto());
			res.setSurname(Auditor.getSurname());
			res.setId(result.getId());
			res.setVersion(result.getVersion());

			final CreditCard creditCard = Auditor.getCreditCard();
			res.setUserAccount(result.getUserAccount());
			res.setSocialProfiles(result.getSocialProfiles());

			creditCard.setBrandName(Auditor.getCreditCard().getBrandName());
			creditCard.setCvv(Auditor.getCreditCard().getCvv());
			creditCard.setExpiryMonth(Auditor.getCreditCard().getExpiryMonth());
			creditCard.setExpiryYear(Auditor.getCreditCard().getExpiryYear());
			creditCard.setHolderName(Auditor.getCreditCard().getHolderName());
			creditCard.setNumber(Auditor.getCreditCard().getNumber());
			res.setCreditCard(creditCard);
			res.setVat(Auditor.getVat());
		}
		this.validator.validate(res, binding);

		return res;
	}

	/************************************************************************************************/

	public Auditor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Auditor result;

		result = this.auditorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkIfAuditor() {
		boolean res = false;

		Collection<Authority> authority;
		authority = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authority)
			if (a.getAuthority().equals(Authority.AUDITOR))
				res = true;
		Assert.isTrue(res);
	}

	public Auditor findByUsername(final String username) {
		Assert.notNull(username);

		return this.auditorRepository.findByUserName(username);
	}

	public void flush() {
		this.auditorRepository.flush();
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
