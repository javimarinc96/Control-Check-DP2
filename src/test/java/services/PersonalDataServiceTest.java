
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.PersonalData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PersonalDataServiceTest extends AbstractTest {

	//Service under test ---------------------------

	//D) data coverage 68.6%

	@Autowired
	private PersonalDataService	personalDataService;

	@Autowired
	private RookieService		rookieService;


	//Caso de uso en el que queremos editar nuestro inception record

	protected void editPersonalDataTemplate(final String username, final int curriculumId, final String fullName, final String statement, final String phone, final String gitProfile, final String linkedProfile, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			final PersonalData pd = this.personalDataService.create(curriculumId);

			pd.setFullName(fullName);
			pd.setGitHubProfile(gitProfile);
			pd.setLinkedInProfile(linkedProfile);
			pd.setPhoneNumber(phone);
			pd.setStatement(statement);

			this.personalDataService.save(pd);
			this.personalDataService.flush();

			super.unauthenticate();

		}

		catch (final Throwable oops) {
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

	// Drivers ----------------------------------------------------------------------

	@Test
	public void driverEditPersonalData() {

		final Object testingData[][] = {

			// Editar personalData logueado como rookie y con datos validos -> true
			{
				"rookie1", 75, "Rookie Campos", "Statement1", "646770976", "http://www.linkedin.com", "http://www.github.com", null
			},
			// Editar personalData logueado como company y con datos validos -> false
			{
				"company1", 75, "Rookie Campos", "Statement1", "646770976", "http://www.linkedin.com", "http://www.github.com", IllegalArgumentException.class
			},

		};

		System.out.println("---EDIT----");

		for (int i = 0; i < testingData.length; i++)
			this.editPersonalDataTemplate((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}

}
