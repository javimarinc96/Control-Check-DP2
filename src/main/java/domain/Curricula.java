
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;



@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {

	// Attributes

	// Relationships ----------------------------------------------------------
	private PersonalData					personalData;
	private Collection<PositionData>		positionsData;
	private Collection<EducationData>		educationsData;
	private MiscellaneousData				miscellaneousData;


	// RELATIONSHIPS ---------------------------------------------------

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final PersonalData personalData) {
		this.personalData = personalData;
	}

	@Valid
	@OneToMany (cascade = CascadeType.ALL)
	public Collection<PositionData> getPositionsData() {
		return this.positionsData;
	}

	public void setPositionsData(final Collection<PositionData> positionsData) {
		this.positionsData = positionsData;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EducationData> getEducationsData() {
		return this.educationsData;
	}

	public void setEducationsData(final Collection<EducationData> educationsData) {
		this.educationsData = educationsData;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public MiscellaneousData getMiscellaneousData() {
		return this.miscellaneousData;
	}

	public void setMiscellaneousData(final MiscellaneousData miscellaneousData) {
		this.miscellaneousData = miscellaneousData;
	}


}
