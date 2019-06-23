
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private AuditService	auditService;


	@Test
	public void driverAssignPosition() {
		final Object testingData[][] = {

			//SNETENCE COVERAGE 100%

			//An actor who is authenticated as an auditor must be able to:
			//1. Self-assign a position to audit it.
			// test positivo
			{
				"auditor1", null,
			}, {

				//test negativo incumpliendo la regla de negocio de que el que crea el audit sea un auditor
				"rookie1", IllegalArgumentException.class,
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testAssignPosition((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void testAssignPosition(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {
			super.authenticate(username);

			final Audit audit = this.auditService.create();
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverAvgSalary() {
		final Object testingData[][] = {
			//Sentece coverage 100%

			//An audit can be updated or deleted as long as its saved in draft mode.
			{
				//test positivo intentado borrar siendo un auditor
				"auditor1", null,
			}, {
				//test negativo intentando borrar siendo un rookie
				"rookie1", IllegalArgumentException.class,
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.testDeleteAudit((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void testDeleteAudit(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			this.auditService.delete(239);

			super.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
