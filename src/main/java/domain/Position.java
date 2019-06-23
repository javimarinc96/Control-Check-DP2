
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	// Attributes ---------------------------------------------------------------------------

	private String	ticker;
	private String	title;
	private String	description;
	private Date	deadline;
	private String	profileRequired;
	private String	skillsRequired;
	private String	technologyRequired;
	private double	salaryOffered;
	private boolean	draftMode;

	private Company	company;


	// Getters & setters ---------------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^\\w{4}-\\d{4}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(final Date deadline) {
		this.deadline = deadline;
	}

	@NotBlank
	@SafeHtml
	public String getProfileRequired() {
		return this.profileRequired;
	}

	public void setProfileRequired(final String profileRequired) {
		this.profileRequired = profileRequired;
	}

	@NotBlank
	@SafeHtml
	public String getSkillsRequired() {
		return this.skillsRequired;
	}

	public void setSkillsRequired(final String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	@NotBlank
	@SafeHtml
	public String getTechnologyRequired() {
		return this.technologyRequired;
	}

	public void setTechnologyRequired(final String technologyRequired) {
		this.technologyRequired = technologyRequired;
	}

	@Min(0)
	@Digits(integer = 10, fraction = 2)
	public double getSalaryOffered() {
		return this.salaryOffered;
	}

	public void setSalaryOffered(final double salaryOffered) {
		this.salaryOffered = salaryOffered;
	}

	public boolean getDraftMode() {
		return this.draftMode;
	}

	public void setDraftMode(final boolean draftMode) {
		this.draftMode = draftMode;
	}

	// Relationships ----------------------------------------------------------

	@NotNull
	@ManyToOne(optional = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(final Company company) {
		this.company = company;
	}

}
