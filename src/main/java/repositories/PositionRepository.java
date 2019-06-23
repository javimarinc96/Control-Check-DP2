
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Position;
import domain.Problem;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	@Query("select p from Position p where p.company.id=?1")
	Collection<Position> findAllPositionsByCompany(int companyId);

	@Query("select p from Problem p where p.position.id=?1")
	Collection<Problem> findAllProblemsByPosition(int positionId);

	@Query("select p from Position p where p.id not in (select a.position.id from Audit a)")
	Collection<Position> findFreePositions();

}
