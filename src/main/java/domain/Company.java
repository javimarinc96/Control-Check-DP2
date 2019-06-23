
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends Actor {

	// Attributes ---------------------------------------------------------------------------

	private String	commercialName;
	private Double  score;
	
	
	// Getters & setters ---------------------------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCommercialName() {
		return this.commercialName;
	}

	public void setCommercialName(final String commercialName) {
		this.commercialName = commercialName;
	}
	
	public Double getScore() {
		return this.score;
	}

	public void setScore (final Double score) {
		this.score = score;
	}

}
