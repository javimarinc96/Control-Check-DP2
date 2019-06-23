
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CreditCard;
import domain.Provider;
import forms.ProviderForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProviderServiceTest extends AbstractTest {

	// Service under test---------------------------
	@Autowired
	private ProviderService	providerService;

	@Autowired
	private ProviderForm	providerForm;


	// Caso de uso en el que queremos crear nuestro Provider
	protected void createProviderTemplate(final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.providerService.create();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

		if (expected == null && caught == null)
			System.out.println("---Los datos son correctos como era esperado----");
		else if (expected != null && caught != null && expected.equals(caught)) {
			System.out.println("---Los datos son incorrectos o violan las restricciones como era esperado----");
			final String excepcion = expected.toString();
			System.out.println(excepcion);
		}
	}

	@Test
	public void driverCreate() {

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a provider.

		final Object testingData[][] = {
			{
				null, "Create correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createProviderTemplate((Class<?>) testingData[i][0]);
	}

	@Test
	public void driverSaveRookie() {

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a company or a rookie.
		//A*: 8. An actor who is authenticated must be able to: 2. Edit his or her personal data.

		final CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("VISA");
		creditCard.setNumber("4081325248931672");
		creditCard.setCvv(403);
		creditCard.setExpiryMonth(07);
		creditCard.setExpiryYear(11);
		creditCard.setHolderName("Francisco Javier Provider");

		final Object testingData[][] = {

			{
				super.getEntityId("provider1"), "NAME", "SURNAME", "ADDRESS", "627027569", "pepe@gmail.com", "prov1", 0.21, creditCard, null, "test positivo"
			},
			//B: A valid email is needed to create or edit personal data of a provider
			{
				super.getEntityId("provider1"), "NAME", "SURNAME", "ADDRESS", "627027569", null, "prov1", 0.21, creditCard, ConstraintViolationException.class, "Se intenta guardar con el email en nulo"
			},
			//B: A valid email is needed to create or edit personal data of a rookie
			{
				super.getEntityId("provider2"), "NAME", "SURNAME", "ADDRESS", "627027569", "pepegmail.com", "prov2", 0.21, creditCard, ConstraintViolationException.class, "Se intenta guardar sin email, rellenando la opción pero sin el arroba"
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.saveProviderTemplate((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(double) testingData[i][7], (int) testingData[i][8], (Class<?>) testingData[i][9], (String) testingData[i][10]);
	}

	protected void saveProviderTemplate(final Integer providerId, final String name, final String surname, final String address, final String phone, final String email, final String make, final double vat, final int creditCardId, final Class<?> expected,
		final String explanation) {

		Class<?> caught = null;

		try {

			final Provider p = this.providerService.findOne(providerId);
			p.setName(name);
			p.setSurname(surname);
			p.setAddress(address);
			p.setPhoneNumber(phone);
			p.setEmail(email);
			p.setMake(make);
			this.providerService.save(p);
			this.providerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
