
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Position;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select p from Position p where p.ticker LIKE %?1%")
	Collection<Position> findPositionsByKeyWordTicker(String keyWord);

	@Query("select p from Position p where p.title LIKE %?1%")
	Collection<Position> findPositionsByKeyWordTitle(String keyWord);

	@Query("select p from Position p where p.description LIKE %?1%")
	Collection<Position> findPositionsByKeyWordDescription(String keyWord);

	@Query("select p from Position p where p.skillsRequired LIKE %?1%")
	Collection<Position> findPositionsByKeyWordSkills(String keyWord);

	@Query("select p from Position p where p.technologyRequired LIKE %?1%")
	Collection<Position> findPositionsByKeyWordTechnologies(String keyWord);

	@Query("select p from Position p where p.profileRequired LIKE %?1%")
	Collection<Position> findPositionsByKeyWordProfile(String keyWord);

	@Query("select p from Position p where p.deadline LIKE ?1")
	Collection<Position> findPositionsByDeadline(Date deadline);

	@Query("select p from Position p where p.deadline < ?1")
	Collection<Position> findPositionsByMaximumDeadline(Date maximumDeadline);

	@Query("select p from Position p where p.salaryOffered >= ?1")
	Collection<Position> findPositionsByMinimumSalary(Double minimumSalary);
}
