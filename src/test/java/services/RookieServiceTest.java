
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
import domain.Rookie;
import forms.RookieForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RookieServiceTest extends AbstractTest {

	// Service under test---------------------------
	@Autowired
	private RookieService	rookieService;

	@Autowired
	private RookieForm		rookieForm;


	// Templates -------------------------------------------------------

	// Caso de uso en el que queremos crear nuestra position
	protected void createRookieTemplate(final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.rookieService.create();

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

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a company or a rookie.

		final Object testingData[][] = {
			{
				null, "Create correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createRookieTemplate((Class<?>) testingData[i][0]);
	}

	@Test
	public void driverSaveRookie() {

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a company or a rookie.
		//A*: 8. An actor who is authenticated must be able to: 2. Edit his or her personal data.
		//C: 100 % sentence coverage

		final CreditCard creditCard = new CreditCard();
		creditCard.setBrandName("VISA");
		creditCard.setNumber("4039117588928035");
		creditCard.setCvv(111);
		creditCard.setExpiryMonth(12);
		creditCard.setExpiryYear(20);
		creditCard.setHolderName("Alfredo");

		final Object testingData[][] = {

			{
				super.getEntityId("rookie1"), "NAME", "SURNAME", "ADDRESS", "627027569", "pepe@gmail.com", 0.21, creditCard, null, "test positivo"
			},
			//B: A valid email is needed to create or edit personal data of a rookie
			{
				super.getEntityId("rookie1"), "NAME", "SURNAME", "ADDRESS", "627027569", null, 0.21, creditCard, ConstraintViolationException.class, "Se intenta guardar con el email en nulo"
			},
			//B: A valid email is needed to create or edit personal data of a rookie
			{
				super.getEntityId("rookie2"), "NAME", "SURNAME", "ADDRESS", "627027569", "pepegmail.com", 0.21, creditCard, ConstraintViolationException.class, "Se intenta guardar sin email, rellenando la opción pero sin el arroba"
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.saveRookieTemplate((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (double) testingData[i][6],
				(int) testingData[i][7], (Class<?>) testingData[i][8], (String) testingData[i][9]);
	}
	protected void saveRookieTemplate(final Integer rookieId, final String name, final String surnames, final String address, final String phone, final String email, final double vat, final int creditCardId, final Class<?> expected,
		final String explanation) {

		Class<?> caught = null;

		try {

			final Rookie h = this.rookieService.findOne(rookieId);
			h.setName(name);
			h.setSurname(surnames);
			h.setAddress(address);
			h.setPhoneNumber(phone);
			h.setEmail(email);
			this.rookieService.save(h);
			this.rookieService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
