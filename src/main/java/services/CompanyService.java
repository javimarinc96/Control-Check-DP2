
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

import repositories.CompanyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Company;
import domain.CreditCard;
import domain.SocialProfile;
import forms.CompanyForm;

@Service
@Transactional
public class CompanyService {

	// Managed repository
	// -------------------------------------------------------------
	@Autowired
	private CompanyRepository		companyRepository;

	// Supporting services
	// -------------------------------------------------------------

	@Autowired(required = false)
	private Validator				validator;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	// CRUD methods
	// ------------------------------------------------------------------

	public Company create() {
		final Company result = new Company();
		final CreditCard creditCard = new CreditCard();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

		authority.setAuthority(Authority.COMPANY);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setSocialProfiles(socialProfiles);
		return result;

	}
	public Company findOne(final int companyId) {
		final Company result = this.companyRepository.findOne(companyId);
		//Assert.notNull(result);
		return result;
	}

	public Company findOneNoAssert(final int companyId) {
		final Company result = this.companyRepository.findOne(companyId);

		return result;
	}

	public Collection<Company> findAll() {
		final Collection<Company> result = this.companyRepository.findAll();
		Assert.notEmpty(result);
		Assert.notNull(result);

		return result;
	}

	public Company save(final Company company) {
		Assert.notNull(company);
		final Company result;
		Assert.isTrue(this.checkDate(company.getCreditCard().getExpiryMonth(), company.getCreditCard().getExpiryYear()));

		if (company.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = company.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);
			final UserAccount ua = this.userAccountRepository.save(userAccount);
			company.setUserAccount(ua);
		}
		result = this.companyRepository.save(company);

		return result;
	}
	public void delete(final Company company) {
		Assert.notNull(company);

		this.companyRepository.delete(company);

	}

	// Other methods
	// -----------------------------------------------------------------

	public Company reconstruct(final CompanyForm companyForm, final BindingResult binding) {
		final Company result = this.create();
		result.getUserAccount().setPassword(companyForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(companyForm.getUserAccount().getUsername());

		result.getCreditCard().setBrandName(companyForm.getCreditCard().getBrandName());
		result.getCreditCard().setCvv(companyForm.getCreditCard().getCvv());
		result.getCreditCard().setExpiryMonth(companyForm.getCreditCard().getExpiryMonth());
		result.getCreditCard().setExpiryYear(companyForm.getCreditCard().getExpiryYear());
		result.getCreditCard().setHolderName(companyForm.getCreditCard().getHolderName());
		result.getCreditCard().setNumber(companyForm.getCreditCard().getNumber());

		result.setCommercialName(companyForm.getCommercialName());
		result.setAddress(companyForm.getAddress());
		result.setEmail(companyForm.getEmail());
		result.setName(companyForm.getName());
		result.setPhoneNumber(companyForm.getPhoneNumber());
		result.setPhoto(companyForm.getPhoto());
		result.setSurname(companyForm.getSurname());
		result.setVat(companyForm.getVat());

		this.validator.validate(result, binding);

		return result;
	}
	public Company reconstruct(final Company company, final BindingResult binding) {
		Company result = new Company();
		final Company res = new Company();

		if (company.getId() == 0)
			result = company;
		else {
			result = this.findOne(company.getId());
			final CreditCard creditCard = company.getCreditCard();
			res.setAddress(company.getAddress());
			res.setEmail(company.getEmail());
			res.setName(company.getName());
			res.setPhoneNumber(company.getPhoneNumber());
			res.setPhoto(company.getPhoto());
			res.setSurname(company.getSurname());
			res.setIsBanned(false);
			res.setId(result.getId());
			res.setVersion(result.getVersion());
			res.setUserAccount(result.getUserAccount());
			res.setSocialProfiles(result.getSocialProfiles());

			creditCard.setBrandName(company.getCreditCard().getBrandName());
			creditCard.setCvv(company.getCreditCard().getCvv());
			creditCard.setExpiryMonth(company.getCreditCard().getExpiryMonth());
			creditCard.setExpiryYear(company.getCreditCard().getExpiryYear());
			creditCard.setHolderName(company.getCreditCard().getHolderName());
			creditCard.setNumber(company.getCreditCard().getNumber());
			res.setCreditCard(creditCard);
			res.setVat(company.getVat());
			res.setCommercialName(company.getCommercialName());

		}
		this.validator.validate(res, binding);

		return res;
	}
	public Company findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Company result;

		result = this.companyRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Company findByPrincipal() {
		Company result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	public Company findOneByUsername(final String username) {
		Assert.notNull(username);

		return this.companyRepository.findByUserName(username);
	}

	public void flush() {
		this.companyRepository.flush();
	}

	public Double obtainScore(final int id) {
		double res = 0.0;

		res = this.companyRepository.avgScore(id);

		return res;
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
