
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Position;
import domain.Problem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProblemServiceTest extends AbstractTest {

	// Services and repositories
	@Autowired
	private ProblemService		problemService;

	@Autowired
	private CompanyService	companyService;
	
	@Autowired
	private PositionService	positionService;




	//Caso de uso en el que queremos registrar (crear) un problem en el sistema
	protected void createProblemTemplate(final String username, final String title, final String statement, final String hint,final String attachment, final Boolean draft, final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.startTransaction();

			super.authenticate(username);

			final Problem prob = this.problemService.create();
			Position p = this.positionService.findOne(30);

			prob.setTitle(title);
			prob.setAttachments(attachment);
			prob.setHint(hint);
			prob.setDraftMode(draft);
			prob.setStatement(statement);
			prob.setPosition(p);

			this.problemService.save(prob);
			this.problemService.flush();

			super.unauthenticate();

		}

		catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.rollbackTransaction();
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

	//Caso de uso en el que actualizamos un Problem
	protected void editProblemTemplate(final String username,final int id, final String title, final String statement, final String hint,final String attachment, final Boolean draft, final Class<?> expected) {
		Class<?> caught = null;

		try {
			super.authenticate(username);

			final Problem prob = this.problemService.findOne(id);

			prob.setTitle(title);
			prob.setAttachments(attachment);
			prob.setHint(hint);
			prob.setDraftMode(draft);
			prob.setStatement(statement);

			this.problemService.save(prob);
			this.problemService.flush();

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

	//Caso de uso en el que se muestra una Problem
	protected void showProblemTemplate(final String username, final int probId, final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.problemService.findOne(probId);

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

	//Caso en el que queremos listar las Problems de una brotherhood
	protected void listProblemByCompanyTemplate(final String username, final int compId, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);

			this.problemService.getProblemsByCompany(compId);

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

	//Caso en el que queremos listar las Problems
	protected void listProblemTemplate(final String username, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);

			this.problemService.findAll();

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

	//Caso en el que queremos borrar una Problem
	public void deleteProblemTemplate(final String username, final int probId, final Class<?> expected) {

		Class<?> caught = null;

		try {

			this.authenticate(username);


			// Borramos
			this.problemService.delete(probId);


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

	// Drivers ----------------------------------------------------------------------

	@Test
	public void driverCreateProblem() {
		//A: Req 10 .5 Creating probessions (Problems) 
		final Object testingData[][] = {

			//B: Test positivo
			//C:
			//D:
			{
				"company1", "titulo1", "statement1", "hint1","http://attachment.com", true, null
			},
			//B: Test negativo --> crear una Problem con otro actor logueado
			//C:
			//D:
			{
				"rookie1", "titulo2", "statement2", "hint2","http://attachment.com", true, IllegalArgumentException.class
			},
		};

		System.out.println("---CREATE----");

		for (int i = 0; i < testingData.length; i++)
			this.createProblemTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4],(Boolean) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	@Test
	public void driverEditProblem() {
		//A: Req 10 .5 Editing probessions (Problems) 
		final Object testingData[][] = {

			//B: Test positivo
			//C:
			//D:
				{
					"company1",34, "titulo1", "statement1", "hint1","http://attachment.com", true, null
				},
				//B: Test negativo --> logueado como otro actor
				//C:
				//D:
				{
					"rookie1",34, "titulo1", "statement1", "hint1","http://attachment.com", true, IllegalArgumentException.class
				},
				//B: Test negativo --> intentar editar un problem de otra company
				//C:
				//D:
				{
					"company2",34, "titulo1", "statement1", "hint1","http://attachment.com", true, IllegalArgumentException.class
				}
		};

		System.out.println("---EDIT----");

		for (int i = 0; i < testingData.length; i++)
			this.editProblemTemplate((String) testingData[i][0],(int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],(Boolean) testingData[i][6], (Class<?>) testingData[i][7]);
	}

	@Test
	public void driverShowProblem() {
		//A: Req 10 .5 Showing probessions (Problems) 
		final Object testingData[][] = {
			//B: Test positivo
			//C:
			//D:
			{
				"comapany1", 34, null
			},
			//B: Test negativo --> buscar una id inexistente
			//C:
			//D:
			{
				"company1", 30, IllegalArgumentException.class
			},
		};

		System.out.println("---SHOW----");

		for (int i = 0; i < testingData.length; i++)
			this.showProblemTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverListProblem() {
		//A: Req 10 .5 Listing probessions (Problems) 
		final Object testingData[][] = {
			// Se accede con brotherhood
			{
				"company1", null
			},
			// Se accede con member
			{
				"rookie1", null
			},
			//  Se accede con admin 
			{
				"admin", null
			},

			//  Se accede con usuario no autentificado 
			{
				null, null
			}
		};

		System.out.println("---LIST----");

		for (int i = 0; i < testingData.length; i++)
			this.listProblemTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverListByCompanyProblem() {
		//A: Req 10 .5 Listing probessions (Problems) 
		final Object testingData[][] = {
			//B: Test positivo
			//C:
			//D:
				{
					"company1", 28, null
				},
			//B: Test positivo
			//C:
			//D:
				{
					"rookie1", 28, IllegalArgumentException.class
				},
			//B: Test positivo
			//C:
			//D: 
			{
				"admin", 29, IllegalArgumentException.class
			},
			//B: Test positivo
			//C:
			//D:
			{
				null, 29, IllegalArgumentException.class
			},
			//B: Test negativo --> se accede con una id inexistente
			//C:
			//D:
			{
				null, 32234, IllegalArgumentException.class
			}
		};

		System.out.println("---LIST BY COMPANY----");

		for (int i = 0; i < testingData.length; i++)
			this.listProblemByCompanyTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteProblem() {
		//A: Req 10 .5 Deleting probessions (Problems) 
		final Object testingData[][] = {
			//B: Test positivo
			//C:
			//D:
			{
				"company1", 34, null
			},
			//B: Test negativo ---> se accede con la id de un objeto que NO esta en draft mode
			//C:
			//D:
			{
				"rookie1", 34, IllegalArgumentException.class
			},
			//B: Test negativo --> se accede con otro actor logueado
			//C:
			//D:
			{
				"admin", 34, IllegalArgumentException.class
			},
			//B: Test negativo --> intentar borrar un problem de otra company
			//C:
			//D:
			{
				"company2", 34, IllegalArgumentException.class
			},
			//B: Test negativo --> intentar borrar un problem en final mode
			//C:
			//D:
			{
				"company2", 36, IllegalArgumentException.class
			}
			
		};

		System.out.println("---DELETE----");
		for (int i = 0; i < testingData.length; i++)
			this.deleteProblemTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}