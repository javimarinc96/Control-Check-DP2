
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Provider;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorshipServiceTest extends AbstractTest {

	@Autowired
	SponsorshipService	sponsorshipService;

	@Autowired
	ProviderService		providerService;


	@Test
	public void driverCreateSponsorship() {

		//100% sentence coverage

		final Object testingData[][] = {
			//Test positivos listar las sponsorships en funcion de estado de una company
			{
				"provider1", null
			}, {

				//Test negativo siendo un Rookie
				"rookie1", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.createSponsorshipTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void createSponsorshipTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			super.authenticate(username);
			this.sponsorshipService.create();
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

	//	protected void listSponsorshipProviderTemplate(final int providerId, final Class<?> expected) {
	//		Class<?> caught = null;
	//
	//		try {
	//
	//			this.sponsorshipService.findSponsorshipsByProvider(providerId);
	//
	//		} catch (final Throwable oops) {
	//			caught = oops.getClass();
	//		}
	//
	//		this.checkExceptions(expected, caught);
	//
	//		if (expected == null && caught == null)
	//			System.out.println("---Los datos son correctos como era esperado----");
	//		else if (expected != null && caught != null && expected.equals(caught)) {
	//			System.out.println("---Los datos son incorrectos o violan las restricciones como era esperado----");
	//			final String excepcion = expected.toString();
	//			System.out.println(excepcion);
	//		}
	//	}
	//
	//	@Test
	//	public void driverListSponsorship() {
	//
	//		//100% sentence coverage
	//
	//		final Object testingData[][] = {
	//			//Test positivos listar las sponsorships de un provider
	//			{
	//				179, null
	//			}, {
	//
	//				//Test negativo siendo un rookie
	//				0, IllegalArgumentException.class
	//			}
	//
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++)
	//			this.listSponsorshipProviderTemplate((int) testingData[i][0], (Class<?>) testingData[i][1]);
	//	}

	@Test
	public void driverSaveSponsorship() {

		//A: 7.1 An actor who is not authenticated must be able to: 1. Register to the system as a company or a rookie.
		//A*: 8. An actor who is authenticated must be able to: 2. Edit his or her personal data.
		//C: 100 % sentence coverage

		final Object testingData[][] = {

			{
				//B: A user registered as provider must be able to: Update a sponsorship
				230, "provider1", "http://www.movistarplus.es/recorte/t/fotograma391/825831abaa56139cabd82379dd350171", "https://www.laresistencia.movistarplus.com", this.providerService.findByUserName("provider1"), null, "test positivo"
			},
			//B: A provider is the only who can update a sponsorship
			{
				231, "rookie1", "http://www.movistarplus.es/recorte/t/fotograma391/825831abaa56139cabd82379dd350171", "https://www.laresistencia.movistarplus.com", this.providerService.findByUserName("rookie1"), IllegalArgumentException.class,
				"Este actor no puede actualizar una sponsorship"
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.saveSponsorshipTemplate((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Provider) testingData[i][4], (Class<?>) testingData[i][5], (String) testingData[i][6]);
	}
	protected void saveSponsorshipTemplate(final Integer sponsorshipId, final String username, final String banner, final String targetPage, final Provider provider, final Class<?> expected, final String explanation) {

		Class<?> caught = null;

		try {
			super.authenticate(username);
			final Sponsorship h = this.sponsorshipService.findOne(sponsorshipId);
			h.setBanner(banner);
			h.setTargetPage(targetPage);

			this.sponsorshipService.save(h);
			this.sponsorshipService.flush();

			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
