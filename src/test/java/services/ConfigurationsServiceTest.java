
package services;

import domain.Actor;
import domain.Configurations;
import domain.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ConfigurationsServiceTest extends AbstractTest {

	// Service under test -------------------------------------
	@Autowired
	private ConfigurationsService	configurationsService;

	// Another supported services -----------------------------
	@Autowired
	private ActorService	actorService;


	/*
	 * 9. An actor who is authenticated as a company must be able to:
	 * 1. Manage their positions, which includes listing, showing, creating, updating, and de-
	 * leting them. Positions can be saved in draft mode; they are not available publicly
	 * until they are saved in final mode. Once a position is saved in final mode, it cannot
	 * be further edited, but it can be cancelled. A position cannot be saved in final mode
	 * unless there are at least two problems associated with it.
	 */

	// Templates -------------------------------------------------------
	// Caso de uso en el que queremos editar nuestra configuration
	protected void editConfigurationsTemplate(final String username, final String systemName, final String banner, final String welcomeMessageEn, final String welcomeMessageEs, final String countryCode, final Collection<String> brandNames, final int cacheTime, final int finderMaxResult, final Collection<String> spamwords, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			final Configurations c = configurationsService.getConfiguration();

			c.setSystemName(systemName);
			c.setBanner(banner);
			c.setWelcomeMessageEn(welcomeMessageEn);
			c.setWelcomeMessageEs(welcomeMessageEs);
			c.setCountryCode(countryCode);
			c.setBrandNames(brandNames);
			c.setCacheTime(cacheTime);
			c.setFinderMaxResult(finderMaxResult);
			c.setSpamWords(spamwords);

			this.configurationsService.save(c);
			this.configurationsService.flush();

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


	// Drivers -------------------------------------------------------------

	@Test
	public void driverEditConfigurations() {

		Collection<String> brandNames = new ArrayList<String>();
		brandNames.add("VISAAA");
		Collection<String> spamwords = new ArrayList<String>();
		spamwords.add("SORTEO AMAZONGA");

		final Object testingData[][] = {

			// Editar configuration logueado como admin y con datos validos -> true
			{
				"admin", "systemmmm", "https://www.instagram.com", "Welcome English", "Hola españolll", "+13", brandNames, 12, 33, spamwords, null
			},
			// Editar configuration logueado como admin y con datos no validos -> false
			{
				"admin", "systemmmm", "https://www.instagram.com", "Welcome English", "Hola españolll", "+13", null, 12, 33, spamwords, IllegalAccessException.class
			},
			// Editar configuration logueado como admin y con datos no validos -> false
			{
				"admin", "systemmmm", "thisisnotaurl", "Welcome English", "Hola españolll", "+13", brandNames, 12, 33, spamwords, ConstraintViolationException.class
			},
			// Editar configuration logueado como company y con datos validos -> false
			{
				"company1", "systemmmm", "https://www.instagram.com", "Welcome English", "Hola españolll", "+13", brandNames, 12, 33, spamwords, IllegalAccessException.class
			},
		};

		System.out.println("---EDIT----");

		for (int i = 0; i < testingData.length; i++)
			this.editConfigurationsTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Collection<String>) testingData[i][6],
				(int) testingData[i][7], (int) testingData[i][8], (Collection<String>) testingData[i][9],(Class<?>) testingData[i][10]);
	}


}
