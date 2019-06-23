
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

	@Query("select p from Problem p join p.position pos join pos.company c where c.id = ?1")
	public Collection<Problem> getProblemsByCompany(int companyId);

	@Query("select p from Problem p join p.position pos where pos.id = ?1")
	public Collection<Problem> getProblemsByPosition(int positionId);

}
