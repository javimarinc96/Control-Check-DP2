
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CompanyServiceTest extends AbstractTest {

	// Service under test---------------------------
	@Autowired
	private CompanyService	companyService;

	@Autowired
	private PositionService	positionService;


	// Templates -------------------------------------------------------

	// Caso de uso en el que queremos crear nuestra position
	protected void createCompanyTemplate(final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.companyService.create();

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

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a company or a company.

		final Object testingData[][] = {
			{
				null, "Create correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createCompanyTemplate((Class<?>) testingData[i][0]);
	}

	@Test
	public void driverSaveCompany() {

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a company or a company.
		//A*: 8. An actor who is authenticated must be able to: 2. Edit his or her personal data.
		//C: 100 % sentence coverage

		final Object testingData[][] = {

			{
				super.getEntityId("company1"), "NAME", "SURNAME", "ADDRESS", "627027569", "pepe@gmail.com", 0.21, 135, "Comercial Name 1", null, "test positivo"
			},
			//B: A valid email is needed to create or edit personal data of a company
			{
				super.getEntityId("company1"), "NAME", "SURNAME", "ADDRESS", "627027569", null, 0.21, 135, "Comercial Name 2", ConstraintViolationException.class, "Se intenta guardar con el email en nulo"
			},
			//B: A valid email is needed to create or edit personal data of a company
			{
				super.getEntityId("company2"), "NAME", "SURNAME", "ADDRESS", "627027569", "pepegmail.com", 0.21, 135, "Comercial Name 3", ConstraintViolationException.class, "Se intenta guardar sin email, rellenando la opción pero sin el arroba"
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.saveCompanyTemplate((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (double) testingData[i][6],
				(int) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9], (String) testingData[i][10]);
	}
	protected void saveCompanyTemplate(final Integer companyId, final String name, final String surnames, final String address, final String phone, final String email, final double vat, final int creditCardId, final String commercialName,
		final Class<?> expected, final String explanation) {

		Class<?> caught = null;

		try {

			final Company h = this.companyService.findOne(companyId);
			h.setName(name);
			h.setSurname(surnames);
			h.setAddress(address);
			h.setPhoneNumber(phone);
			h.setEmail(email);
			this.companyService.save(h);
			this.companyService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
