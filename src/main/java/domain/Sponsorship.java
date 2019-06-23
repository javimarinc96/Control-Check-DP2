
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String		banner;
	private String		targetPage;

	//Relationships
	private Provider	provider;
	private Position	position;


	@URL
	@NotBlank
	@SafeHtml
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@URL
	@NotBlank
	@SafeHtml
	public String getTargetPage() {
		return this.targetPage;
	}

	public void setTargetPage(final String targetPage) {
		this.targetPage = targetPage;
	}

	@ManyToOne(optional = false)
	@NotNull
	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(final Provider provider) {
		this.provider = provider;
	}

	@ManyToOne(optional = false)
	@NotNull
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

}
