
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
public class AdministratorServiceTest extends AbstractTest {

	// Service under test---------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Templates -------------------------------------------------------

	protected void testAvgAppRookie(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST AVG SALARY --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double avgSalary = this.administratorService.avgAppRookie();
			final String print = avgSalary.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testMinAppRookie(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST MIN SALARY --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double minSalary = this.administratorService.minAppRookie();
			final String print = minSalary.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testMaxAppRookie(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST MAX SALARY --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double maxSalary = this.administratorService.maxAppRookie();
			final String print = maxSalary.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testStddevAppRookie(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST STDDEV SALARY --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double stddevSalary = this.administratorService.stddevAppRookie();
			final String print = stddevSalary.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testAvgAuditScore(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST AVG CURRICULAS--------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double avgCurriculas = this.administratorService.avgAuditScore();
			final String print = avgCurriculas.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testMinAuditScore(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST MIN CURRICULAS--------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Integer minCurriculas = this.administratorService.minAuditScore();
			final String print = minCurriculas.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testMaxAuditScore(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST MAX CURRICULAS --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Integer maxCurriculas = this.administratorService.maxAuditScore();
			final String print = maxCurriculas.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testStddevAuditScore(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST STDDEV CURRICULAS  --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double stddevCurriculas = this.administratorService.stddevAuditScore();
			final String print = stddevCurriculas.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testAvgItemsProvider(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST AVG RESULTS --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double avgResults = this.administratorService.avgItemsProvider();
			final String print = avgResults.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testMinitemsProvider(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST MIN RESULTS --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double minResults = this.administratorService.minItemsProvider();
			final String print = minResults.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testMaxItemsProvider(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST MAX RESULTS --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double maxResults = this.administratorService.maxItemsProvider();
			final String print = maxResults.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testStddevItemsProvider(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST STDDEV RESULTS --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double stddevResults = this.administratorService.stddevItemsProvider();
			final String print = stddevResults.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	protected void testRatioFinders(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			System.out.println("------------------------------------TEST RATIO FINDERS --------------------------------------------------");

			super.authenticate(username);

			@SuppressWarnings("unused")
			final Double ratioFinders = this.administratorService.ratioFinders();
			final String print = ratioFinders.toString();
			System.out.println(print);

			super.unauthenticate();
			System.out.println("----------------------------PASSED-------------------------------\r");
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	//DRIVERS

	@Test
	public void driverAvgSalary() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testAvgAppRookie((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverMinSalary() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testMinAppRookie((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverMaxSalary() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testMaxAppRookie((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverStddevSalary() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testStddevAppRookie((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverAvgAuditScore() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testAvgAuditScore((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverMinAuditScore() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testMinAuditScore((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverMaxAuditScore() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testMaxAuditScore((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverStddevAuditScore() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testStddevAuditScore((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverAvgItemsProvider() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testAvgItemsProvider((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverMinItemsProvider() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testMinitemsProvider((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverMaxItemsProvider() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testMaxItemsProvider((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverStddevItemsProvider() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testStddevItemsProvider((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverRatioFindersResults() {

		final Object testingData[][] = {

			// sentence coverage 100%

			// a) Mostrar información de la dashboard como admin
			{
				"admin", null,
			},
			// a) Mostrar información de la dashboard como admin
			// b) Un usuario logueado como administrador debe ser capaz de mostrar ésta información en la dashboard
			{
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testRatioFinders((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

}
