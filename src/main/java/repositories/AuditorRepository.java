
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Auditor;
import domain.Company;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Integer> {

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findById(int id);

	@Query("select a from Auditor a where a.userAccount.username = ?1")
	Auditor findByUserName(String username);

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findByUserAccountId(int id);

}
