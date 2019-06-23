
package forms;

import domain.MiscellaneousData;
import domain.PersonalData;

public class CurriculaForm {

	//Personal Data
	private PersonalData		personalData;
	private MiscellaneousData	miscellaneousData;


	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final PersonalData personalData) {
		this.personalData = personalData;
	}

	public MiscellaneousData getMiscellaneousData() {
		return this.miscellaneousData;
	}

	public void setMiscellaneousData(final MiscellaneousData miscellaneousData) {
		this.miscellaneousData = miscellaneousData;
	}

}
