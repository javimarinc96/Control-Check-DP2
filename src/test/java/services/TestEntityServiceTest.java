
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;
import domain.Position;
import domain.TestEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestEntityServiceTest extends AbstractTest {

	// Service under test -------------------------------------
	@Autowired
	private TestEntityService	testEntityService;
	
	@Autowired
	private RelationEntity2Service	relation2Service;

	@Autowired
	private ActorService	actorService;


	// Templates -------------------------------------------------------

	// Caso de uso en el que queremos crear nuestra position
	protected void createTestEntityTemplate(final String username, final String body, final String photo,  
			final boolean draftMode, final int relation2Id, final Class<?> expected) {
		
		Class<?> caught = null;

		try {
			super.authenticate(username);

			final TestEntity t = this.testEntityService.create();

			RelationEntity2 aud = this.relation2Service.findOne(relation2Id);
					
			t.setBody(body);
			t.setDraftMode(draftMode);
			t.setPhoto(photo);
			t.setRelation2(aud);

			this.testEntityService.save(t);
			this.testEntityService.flush();

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
	public void driverCreateTestEntity() {

		final Object testingData[][] = {

			// Crear position logueado como company y con datos validos -> true
			{
				"company1", "body1", "http://www.photo1.com", true, 239, null
			},
			// Crear position logueado como company y con datos no validos -> false
			{
				"auditor1", "body2", "http://www.photo2.com", true, 240, IllegalAccessException.class
			},
		};

		System.out.println("---CREATE----");

		for (int i = 0; i < testingData.length; i++)
			this.createTestEntityTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Boolean) testingData[i][3], (int) testingData[i][4],
			 (Class<?>) testingData[i][5]);
	}


}
