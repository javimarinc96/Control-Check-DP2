
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class TestEntity extends DomainEntity {

	// Attributes ---------------------------------------------------------------------------

	private String		ticker;
	private Date		moment;
	private String		body;
	private String      photo;
	private Boolean		draftMode;

	// Relationships ----------------------------------------------------------
//	private RelationEntity1	relation1;
//	private RelationEntity2	relation2;


	// Getters & setters ---------------------------------------------------------------------
	
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^\\w{4}-\\d{6}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	
	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@SafeHtml
	@Length(max = 100)
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	public Boolean getDraftMode() {
		return this.draftMode;
	}

	public void setDraftMode(final Boolean draftMode) {
		this.draftMode = draftMode;
	}

//	@NotNull
//	@ManyToOne(optional = false)
//	public RelationEntity1 getRelation1() {
//		return this.relation1;
//	}
//
//	public void setRelation1 (final RelationEntity1 relation1) {
//		this.relation1 = relation1;
//	}
//
//	@NotNull
//	@ManyToOne(optional = false)
//	public RelationEntity2 getRelation2() {
//		return this.relation2;
//	}
//
//	public void setRelation2 (final RelationEntity2 relation2) {
//		this.relation2 = relation2;
//	}
}
