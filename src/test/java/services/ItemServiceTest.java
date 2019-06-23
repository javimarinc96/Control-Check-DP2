
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Item;
import domain.Provider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ItemServiceTest extends AbstractTest {

	@Autowired
	ItemService		itemService;

	@Autowired
	ProviderService	providerService;


	@Test
	public void driverCreateSponsorship() {

		//100% sentence coverage

		final Object testingData[][] = {
			//Test positivos siendo un Provider
			{
				"provider1", null
			}, {

				//Test negativo siendo un Rookie
				"rookie1", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.createItemTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void createItemTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			super.authenticate(username);
			this.itemService.create();
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
	public void driverSaveItem() {

		final Object testingData[][] = {

			{
				//B: A user registered as provider must be able to: Update a item
				234, "provider1", "name 1", "desc 1", "http://link1.com", this.providerService.findByUserName("provider1"), null, "test positivo"
			},
			//B: A provider is the only who can update a item
			{
				235, "rookie1", "name 1", "desc 1", "http://link1.com", this.providerService.findByUserName("rookie1"), IllegalArgumentException.class, "Este actor no puede actualizar un item"
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.saveItemTemplate((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Provider) testingData[i][5], (Class<?>) testingData[i][6],
				(String) testingData[i][7]);
	}

	protected void saveItemTemplate(final Integer itemId, final String username, final String name, final String description, final String link, final Provider provider, final Class<?> expected, final String explanation) {

		Class<?> caught = null;

		try {
			super.authenticate(username);
			final Item i = this.itemService.findOne(itemId);
			i.setName(name);
			i.setDescription(description);
			i.setLink(link);

			this.itemService.save(i);
			this.itemService.flush();

			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
