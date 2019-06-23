
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
public class Rookie extends Actor {

	private Collection<Curricula>	curriculas;
	private Finder					finder;


	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Curricula> getCurriculas() {
		return this.curriculas;
	}

	public void setCurriculas(final Collection<Curricula> curriculas) {
		this.curriculas = curriculas;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

}
