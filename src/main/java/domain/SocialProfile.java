
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialProfile extends DomainEntity {

	// Attributes

	private String	nick;
	private String	socialNetworkName;
	private String	link;


	@NotBlank
	@SafeHtml
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@NotBlank
	@SafeHtml
	public String getSocialNetworkName() {
		return this.socialNetworkName;
	}

	public void setSocialNetworkName(String socialNetworkName) {
		this.socialNetworkName = socialNetworkName;
	}

	@NotBlank
	@SafeHtml
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
