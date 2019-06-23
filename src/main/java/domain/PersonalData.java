
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class PersonalData extends DomainEntity {

	// Attributes

	private String			fullName;
	private String 			statement;
	private String			phoneNumber;
	private String			gitHubProfile;
	private String			linkedInProfile;

	
	// Relationships --------------------

	// Getters & setters
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	public String getGitHubProfile() {
		return gitHubProfile;
	}


	public void setGitHubProfile(String gitHubProfile) {
		this.gitHubProfile = gitHubProfile;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	public String getLinkedInProfile() {
		return linkedInProfile;
	}

	public void setLinkedInProfile(String linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}

	// RELATIONSHIPS	---------------------------------------
	
}
