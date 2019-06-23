
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PositionServiceTest extends AbstractTest {

	// Service under test -------------------------------------
	@Autowired
	private PositionService	positionService;

	// Another supported services -----------------------------
	//	@Autowired
	//	private CompanyService	companyService;

	@Autowired
	private ActorService	actorService;


	/*
	 * 9. An actor who is authenticated as a administrator must be able to:
	 * 1. Manage the configuration of the system
	 */

	// Templates -------------------------------------------------------

	// Caso de uso en el que queremos crear nuestra position
	protected void createPositionTemplate(final String username, final String title, final String description, final Date deadline, final String profileRequired, final String skillsRequired, final String technologyRequired, final double salaryOffered,
		final boolean draftMode, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			final Position p = this.positionService.create();

			p.setTitle(title);
			p.setDescription(description);
			p.setDeadline(deadline);
			p.setProfileRequired(profileRequired);
			p.setSkillsRequired(skillsRequired);
			p.setTechnologyRequired(technologyRequired);
			p.setSalaryOffered(salaryOffered);
			p.setDraftMode(draftMode);

			this.positionService.save(p);
			this.positionService.flush();

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

	// Caso de uso en el que queremos editar nuestra position
	protected void editPositionTemplate(final String username, final int positionId, final String title, final String description, final Date deadline, final String profileRequired, final String skillsRequired, final String technologyRequired,
		final double salaryOffered, final boolean draftMode, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			final Position p = this.positionService.findOneToEdit(positionId);

			p.setTitle(title);
			p.setDescription(description);
			p.setDeadline(deadline);
			p.setProfileRequired(profileRequired);
			p.setSkillsRequired(skillsRequired);
			p.setTechnologyRequired(technologyRequired);
			p.setSalaryOffered(salaryOffered);
			p.setDraftMode(draftMode);

			this.positionService.save(p);
			this.positionService.flush();

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

	//Caso en el que queremos listar todas las positions
	protected void listAllPositionsTemplate(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);

			this.positionService.findAll();

			this.unauthenticate();

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

	//Caso en el que queremos listar nuestras positions
	protected void listMyPositionsTemplate(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Actor principal = this.actorService.findByPrincipal();

			this.positionService.findAllPositionsByCompany(principal.getId());

			this.unauthenticate();

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

	//Caso en el que queremos mostrar una posicion
	protected void showPositionTemplate(final String username, final int positionId, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);

			this.positionService.findOne(positionId);

			this.unauthenticate();

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

	//Caso en el que queremos borrar una posicion
	public void deletePositionTemplate(final String username, final int positionId, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);

			// Buscamos la position
			//final Position p = this.positionService.findOne(positionId);

			// Borramos
			this.positionService.delete(positionId);

			this.unauthenticate();

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

	// Drivers -------------------------------------------------------------

	@Test
	public void driverCreatePositon() {

		final Date future = new Date(2020, 10, 1, 10, 0);
		final Date past = new Date();

		final Object testingData[][] = {

			// Crear position logueado como company y con datos validos -> true
			{
				"company1", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, null
			},
			// Crear position logueado como company y con datos validos -> true
			{
				"company2", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, null
			},
			// Crear position logueado como company y con datos validos -> true
			{
				"company1", "Tit23232letest1", "Descriptiont223est1", future, "Profile requirem22ents test", "Skills required test", "Technology required test", 18.2, true, null
			},
			// Crear position logueado como company y con datos validos -> true
			{
				"company2", "Titletsdfsfsest1", "sdfsdsdf", future, "Profile requirements test", "Skills required test", "Technology required test", 8.0, true, null
			},
			// Crear position logueado como company y con datos validos -> true
			{
				"company1", "NoTitle", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, null
			},
			// Crear position logueado como company con datos validos y draft mode en false -> true
			{
				"company1", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, false, null
			},
			// Crear position logueado como company con datos validos, pero fecha en pasado -> false
			{
				"company2", "Titletest1", "Descriptiontest1", past, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Crear position logueado como otro usuario y con datos validos -> false
			{
				"rookie1", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Crear position logueado como otro usuario y con datos validos -> false
			{
				"rookie2", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Crear position logueado como otro usuario y con datos validos -> false
			{
				"admin", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Crear position logueado como company y con datos no validos -> false
			{
				"company1", "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", -220.2, true, javax.validation.ConstraintViolationException.class
			},
			// Crear position logueado como company y con datos no validos -> false
			{
				"company1", "", "", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, javax.validation.ConstraintViolationException.class
			},
		};

		System.out.println("---CREATE----");

		for (int i = 0; i < testingData.length; i++)
			this.createPositionTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Double) testingData[i][7], (Boolean) testingData[i][8], (Class<?>) testingData[i][9]);
	}

	@Test
	public void driverEditPositon() {

		final Date future = new Date(2020, 11, 04);
		final Date past = new Date(2018, 11, 04);

		final Object testingData[][] = {

			// Editar position logueado como company y con datos validos -> true
			{
				"company1", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, null
			},
			// Editar position logueado como company y con datos validos -> true
			{
				"company2", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, null
			},
			// Editar position logueado como company y con datos validos -> true
			{
				"company1", 223, "Tit23232letest1", "Descriptiont223est1", future, "Profile requirem22ents test", "Skills required test", "Technology required test", 18.2, true, null
			},
			// Editar position logueado como company y con datos validos -> true
			{
				"company2", 223, "Titletsdfsfsest1", "sdfsdsdf", future, "Profile requirements test", "Skills required test", "Technology required test", 8.0, true, null
			},
			// Editar position logueado como company y con datos validos -> true
			{
				"company1", 223, "NoTitle", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, null
			},
			// Editar position logueado como company con datos validos, pero draft mode en false -> false
			{
				"company1", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, false, IllegalArgumentException.class
			},
			// Editar position logueado como company con datos validos, pero fecha en pasado -> false
			{
				"company2", 223, "Titletest1", "Descriptiontest1", past, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Editar position logueado como otro usuario y con datos validos -> false
			{
				"rookie1", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Editar position logueado como otro usuario y con datos validos -> false
			{
				"rookie2", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Editar position logueado como otro usuario y con datos validos -> false
			{
				"admin", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Editar position logueado como company y con datos no validos -> false
			{
				"company1", 223, "Titletest1", "Descriptiontest1", future, "Profile requirements test", "Skills required test", "Technology required test", -220.2, true, IllegalArgumentException.class
			},
			// Editar position logueado como company y con datos no validos -> false
			{
				"company1", 223, "", "", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
			// Editar position logueado como company y perteneciente a otra company -> false
			{
				"company2", 223, "Titletest1", "Titletest1", future, "Profile requirements test", "Skills required test", "Technology required test", 220.2, true, IllegalArgumentException.class
			},
		};

		System.out.println("---EDIT----");

		for (int i = 0; i < testingData.length; i++)
			this.editPositionTemplate((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Date) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (Double) testingData[i][8], (Boolean) testingData[i][9], (Class<?>) testingData[i][10]);
	}

	@Test
	public void driverListAllPositons() {

		final Object testingData[][] = {

			// Listar position logueado como company -> true
			{
				"company1", null
			},
			// Listar position logueado como company -> true
			{
				"company2", null
			},
			// Listar position logueado como admin -> true
			{
				"admin", null
			},
			// Listar position logueado como rookie -> true
			{
				"rookie1", null
			},
			// Listar position logueado como rookie -> true
			{
				"rookie2", null
			},
			// Listar position sin loguear -> true
			{
				null, null
			},
			// Listar position logueado como actor que no existe -> false
			{
				"actornoexistente", IllegalArgumentException.class
			},
		};

		System.out.println("---LIST ALL----");

		for (int i = 0; i < testingData.length; i++)
			this.listAllPositionsTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverListMyPositons() {

		final Object testingData[][] = {

			// Listar position logueado como company -> true
			{
				"company1", null
			},
			// Listar position logueado como company -> true
			{
				"company2", null
			},
			// Listar position logueado como admin -> false
			{
				"admin", IllegalArgumentException.class
			},
			// Listar position logueado como rookie -> false
			{
				"rookie1", IllegalArgumentException.class
			},
			// Listar position logueado como rookie -> false
			{
				"rookie2", IllegalArgumentException.class
			},
			// Listar position sin loguear -> false
			{
				null, IllegalArgumentException.class
			},
			// Listar position logueado como actor que no existe -> false
			{
				"actornoexistente", IllegalArgumentException.class
			},
		};

		System.out.println("---MY LIST----");

		for (int i = 0; i < testingData.length; i++)
			this.listMyPositionsTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverShowPositon() {

		final Object testingData[][] = {

			// Mostrar position logueado como company -> true
			{
				"company1", 223, null
			},
			// Mostrar position logueado como company -> true
			{
				"company2", 223, null
			},
			// Mostrar position logueado como admin -> true
			{
				"admin", 223, null
			},
			// Mostrar position logueado como rookie -> true
			{
				"rookie1", 223, null
			},
			// Mostrar position logueado como rookie -> true
			{
				"rookie2", 223, null
			},
			// Mostrar position sin loguear -> true
			{
				null, 223, null
			},
			// Mostrar position logueado como actor que no existe -> false
			{
				"actornoexistente", 223, IllegalArgumentException.class
			},
			// Mostrar position con una id inexistente -> false
			{
				"actornoexistente", 12122, IllegalArgumentException.class
			},
		};

		System.out.println("---SHOW----");

		for (int i = 0; i < testingData.length; i++)
			this.showPositionTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeletePositon() {

		final Object testingData[][] = {

			// Borrar position logueado como company -> true
			{
				"company1", 223, null
			},
			// Borrar position logueado como company -> true
			{
				"company2", 223, null
			},
			// Borrar position logueado como admin -> true
			{
				"admin", 223, null
			},
			// Borrar position logueado como rookie -> true
			{
				"rookie1", 223, null
			},
			// Borrar position logueado como rookie -> true
			{
				"rookie2", 223, null
			},
			// Borrar position sin loguear -> true
			{
				null, 223, null
			},
			// Borrar position logueado como actor que no existe -> false
			{
				"actornoexistente", 223, IllegalArgumentException.class
			},
			// Borrar position con una id inexistente -> false
			{
				"company1", 12122, IllegalArgumentException.class
			},
			// Borrar position que no está en draftMode -> false
			{
				"company1", 777999, IllegalArgumentException.class
			},
		};

		System.out.println("---DELETE----");

		for (int i = 0; i < testingData.length; i++)
			this.deletePositionTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
