
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	ApplicationService	applicationService;

	@Autowired
	CompanyService		companyService;

	@Autowired
	RookieService		rookieService;


	@Test
	public void driverCreateApplication() {

		//100% sentence coverage

		final Object testingData[][] = {
			//Test positivos listar las applications en funcion de estado de una company
			{
				"rookie1", null
			}, {

				//Test negativo siendo un member
				"member1", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.createApplicationTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void listApplicationCompanyTemplate(final String username, final String status, final Class<?> expected) {
		Class<?> caught = null;

		try {

			super.authenticate(username);

			this.applicationService.findApplicationsByStatusCompany(status);

			super.unauthenticate();

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

	protected void createApplicationTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			super.authenticate(username);
			this.applicationService.create();
			super.unauthenticate();

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
	protected void listApplicationRookieTemplate(final String username, final String status, final Class<?> expected) {
		Class<?> caught = null;

		try {

			super.authenticate(username);

			this.applicationService.findApplicationsByStatusRookie(status);

			super.unauthenticate();

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
	public void driverListApplication() {

		//100% sentence coverage

		final Object testingData[][] = {
			//Test positivos listar las applications en funcion de estado de una company
			{
				"company1", "PENDING", null
			}, {

				//Test negativo siendo un member
				"member1", "PEDING", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.listApplicationCompanyTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverListRookieApplication() {

		//100% sentence coverage

		final Object testingData[][] = {
			//Test positivos listar las applications en funcion de estado de una company
			{
				"rookie1", "PENDING", null
			}, {

				//Test negativo siendo un member
				"member1", "PEDING", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.listApplicationRookieTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void showApplicationTemplate(final String username, final Integer applicationId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			this.applicationService.findOne(applicationId);

			super.unauthenticate();

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
	public void driverShowApplication() {
		final Object testingData[][] = {
			{
				//Test positivo show application
				"company1", 110, null
			},
			//Test negativo, id invalido
			{
				"company1", 999999, IllegalArgumentException.class
			}, {
				//Test positivo show application
				"rookie1", 110, null
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.showApplicationTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverUpdateApplication() {

		//Sentence coverage 100%
		final Object testingData[][] = {
			{
				//Test positivo show application
				"company1", 110, null
			},
			//Test negativo, id invalido
			{
				"company1", 999999, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.updateApplicationTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void updateApplicationTemplate(final String username, final Integer applicationId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			this.applicationService.acceptApplication(applicationId);

			super.unauthenticate();

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
}
