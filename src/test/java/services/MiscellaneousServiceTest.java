
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.MiscellaneousData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscellaneousServiceTest extends AbstractTest {

	@Autowired
	MiscellaneousDataService	miscellaneousService;


	protected void saveMiscellaneousTemplate(final String username, final Integer curriculumId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			super.authenticate(username);

			final MiscellaneousData m = this.miscellaneousService.create(curriculumId);
			this.miscellaneousService.save(m);
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
	public void driverSaveMiscellaneousData() {
		//Sentece coverage 100%
		final Object testingData[][] = {
			{
				//Test positivo a guardar un miscellaneousdata
				"rookie1", 75, IllegalArgumentException.class
			}, {

				//Test negativo
				"rookie1", 99, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.saveMiscellaneousTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);

	}
}
