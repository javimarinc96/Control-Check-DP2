
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configurations extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String				systemName;
	private String				banner;
	private String				welcomeMessageEn;
	private String				welcomeMessageEs;
	private String				countryCode;
	private Collection<String>	brandNames;
	private int					cacheTime;
	private int					finderMaxResult;
	private Collection<String>	spamWords;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	@NotBlank
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getWelcomeMessageEn() {
		return this.welcomeMessageEn;
	}

	public void setWelcomeMessageEn(final String welcomeMessageEn) {
		this.welcomeMessageEn = welcomeMessageEn;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getWelcomeMessageEs() {
		return this.welcomeMessageEs;
	}

	public void setWelcomeMessageEs(final String welcomeMessageEs) {
		this.welcomeMessageEs = welcomeMessageEs;
	}

	@NotBlank
	@Pattern(regexp = "([+]?\\d{1,2})")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	@NotNull
	@NotEmpty
	@ElementCollection
	public Collection<String> getBrandNames() {
		return this.brandNames;
	}

	public void setBrandNames(final Collection<String> brandNames) {
		this.brandNames = brandNames;
	}

	@Range(min = 1, max = 24)
	public int getCacheTime() {
		return this.cacheTime;
	}

	public void setCacheTime(final int cacheTime) {
		this.cacheTime = cacheTime;
	}

	@Range(min = 1, max = 100)
	public Integer getFinderMaxResult() {
		return this.finderMaxResult;
	}

	public void setFinderMaxResult(final Integer finderMaxResult) {
		this.finderMaxResult = finderMaxResult;
	}

	@NotNull
	@NotEmpty
	@ElementCollection
	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final Collection<String> spamWords) {
		this.spamWords = spamWords;
	}






	// Relationships ----------------------------------------------------------

	// Other methods
}
