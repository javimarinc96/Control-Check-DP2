
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

import repositories.ProviderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.CreditCard;
import domain.Provider;
import domain.SocialProfile;
import forms.ProviderForm;

@Service
@Transactional
public class ProviderService {

	// Manage Repositories
	@Autowired
	private ProviderRepository		providerRepository;

	@Autowired(required = false)
	private Validator				validator;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	//CRUD methods

	public Provider create() {
		final Provider result = new Provider();
		final CreditCard creditCard = new CreditCard();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

		authority.setAuthority(Authority.PROVIDER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setSocialProfiles(socialProfiles);

		return result;

	}

	public Provider save(final Provider provider) {
		Provider res;
		Assert.notNull(provider);
		Assert.isTrue(this.checkDate(provider.getCreditCard().getExpiryMonth(), provider.getCreditCard().getExpiryYear()));

		if (provider.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = provider.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);

			final UserAccount ua = this.userAccountRepository.save(userAccount);
			provider.setUserAccount(ua);
		}
		res = this.providerRepository.save(provider);

		this.folderService.generateFolders(res.getId());

		return res;
	}

	public Provider findOne(final int providerId) {
		final Provider result = this.providerRepository.findOne(providerId);
		Assert.notNull(result);

		return result;
	}

	public Provider findOneNoAssert(final int providerId) {
		final Provider result = this.providerRepository.findOne(providerId);

		return result;
	}

	public Collection<Provider> findAll() {
		final Collection<Provider> result = this.providerRepository.findAll();
		Assert.notNull(result);
		Assert.notEmpty(result);

		return result;
	}

	public void delete(final Provider provider) {
		Assert.notNull(provider);

		this.providerRepository.delete(provider);
	}

	/*** Reconstruct object, check validity and update binding ***/
	public Provider reconstruct(final ProviderForm providerForm, final BindingResult binding) {
		final Provider result = this.create();
		result.getUserAccount().setPassword(providerForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(providerForm.getUserAccount().getUsername());
		//		Assert.isTrue(providerForm.getUserAccount().getPassword() == providerForm.getConfirmPassword());

		result.getCreditCard().setBrandName(providerForm.getCreditCard().getBrandName());
		result.getCreditCard().setCvv(providerForm.getCreditCard().getCvv());
		result.getCreditCard().setExpiryMonth(providerForm.getCreditCard().getExpiryMonth());
		result.getCreditCard().setExpiryYear(providerForm.getCreditCard().getExpiryYear());
		result.getCreditCard().setHolderName(providerForm.getCreditCard().getHolderName());
		result.getCreditCard().setNumber(providerForm.getCreditCard().getNumber());

		result.setAddress(providerForm.getAddress());
		result.setEmail(providerForm.getEmail());
		result.setName(providerForm.getName());
		result.setPhoneNumber(providerForm.getPhoneNumber());
		result.setPhoto(providerForm.getPhoto());
		result.setSurname(providerForm.getSurname());
		result.setVat(providerForm.getVat());
		result.setIsBanned(false);

		result.setMake(providerForm.getMake());

		this.validator.validate(result, binding);

		return result;
	}

	public Provider reconstruct(final Provider provider, final BindingResult binding) {
		Provider result = new Provider();
		final Provider res = new Provider();

		if (provider.getId() == 0)
			result = provider;
		else {
			result = this.findOne(provider.getId());
			final CreditCard creditCard = provider.getCreditCard();
			res.setAddress(provider.getAddress());
			res.setEmail(provider.getEmail());
			res.setName(provider.getName());
			res.setPhoneNumber(provider.getPhoneNumber());
			res.setPhoto(provider.getPhoto());
			res.setSurname(provider.getSurname());
			res.setId(result.getId());
			res.setVersion(result.getVersion());
			res.setUserAccount(result.getUserAccount());
			res.setMake(result.getMake());
			res.setSocialProfiles(result.getSocialProfiles());

			creditCard.setBrandName(provider.getCreditCard().getBrandName());
			creditCard.setCvv(provider.getCreditCard().getCvv());
			creditCard.setExpiryMonth(provider.getCreditCard().getExpiryMonth());
			creditCard.setExpiryYear(provider.getCreditCard().getExpiryYear());
			creditCard.setHolderName(provider.getCreditCard().getHolderName());
			creditCard.setNumber(provider.getCreditCard().getNumber());
			res.setCreditCard(creditCard);
			res.setVat(provider.getVat());
		}
		this.validator.validate(res, binding);

		return res;
	}

	// Other business methods
	public Provider findByPrincipal() {
		Provider result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Provider findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Provider result;

		result = this.providerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Provider findByUserName(final String username) {
		Assert.notNull(username);

		Provider result;

		result = this.providerRepository.findByUserName(username);

		return result;
	}

	public void checkIfProvider() {
		boolean res = false;

		Collection<Authority> authority;
		authority = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authority)
			if (a.getAuthority().equals(Authority.PROVIDER))
				res = true;
		Assert.isTrue(res);
	}

	public void flush() {
		this.providerRepository.flush();
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
