
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rookie;

@Repository
public interface RookieRepository extends JpaRepository<Rookie, Integer> {

	@Query("select a from Rookie a where a.id = ?1")
	Rookie findById(int id);

	@Query("select a from Rookie a where a.userAccount.username = ?1")
	Rookie findByUserName(String username);

	@Query("select a from Rookie a where a.userAccount.id = ?1")
	Rookie findByUserAccountId(int id);

	@Query("select h from Rookie h join h.curriculas p where p.id = ?1")
	Rookie findRookieByCurriculaId(int curriculaId);

	//	@Query("select max(h.  from Rookie h")
	//	Double maxApplicationsPerRookie();

	//	@Query("select h from Rookie h join h.applications a where a.id = ?1")
	//	Rookie findRookieByApplicationId(int applicationId);
}
