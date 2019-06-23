
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	// Attributes ---------------------------------------------------------------------------
	private String		status;
	private String		answerDescription;
	private String		linkCode;
	private Date		momentSubmit;

	private Problem		problem;
	private Position	position;
	private Rookie		rookie;


	// Getters & setters ---------------------------------------------------------------------
	@NotNull
	@Pattern(regexp = "^PENDING|SUBMITTED|APPROVED|REJECTED$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@SafeHtml
	public String getAnswerDescription() {
		return this.answerDescription;
	}

	public void setAnswerDescription(final String answerDescription) {
		this.answerDescription = answerDescription;
	}

	@URL
	@SafeHtml
	public String getLinkCode() {
		return this.linkCode;
	}

	public void setLinkCode(final String linkCode) {
		this.linkCode = linkCode;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentSubmit() {
		return this.momentSubmit;
	}

	public void setMomentSubmit(final Date momentSubmit) {
		this.momentSubmit = momentSubmit;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Problem getProblem() {
		return this.problem;
	}

	public void setProblem(final Problem problem) {
		this.problem = problem;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Rookie getRookie() {
		return this.rookie;
	}

	public void setRookie(final Rookie rookie) {
		this.rookie = rookie;
	}

}
