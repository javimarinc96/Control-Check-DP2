
package forms;

import security.UserAccount;
import domain.CreditCard;

public class CompanyForm {

	private UserAccount	userAccount;
	private String		confirmPassword;
	private String		name;
	private String		surname;
	private String		phoneNumber;
	private String		email;
	private String		address;
	private String		photo;
	private String		vat;
	private String		commercialName;
	private CreditCard	creditCard;


	// Getters and Setters

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCommercialName() {
		return this.commercialName;
	}

	public void setCommercialName(final String commercialName) {
		this.commercialName = commercialName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phoneNumber;
	}
	public void setPhone(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return this.address;
	}
	public void setAddress(final String address) {
		this.address = address;
	}

	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public String getVat() {
		return this.vat;
	}
	public void setVat(final String vat) {
		this.vat = vat;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "RegistrationFormCustomer [Name=" + this.name + "]";
	}

}
