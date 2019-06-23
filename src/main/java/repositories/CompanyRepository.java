
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("select a from Company a where a.id = ?1")
	Company findById(int id);

	@Query("select a from Company a where a.userAccount.username = ?1")
	Company findByUserName(String username);

	@Query("select a from Company a where a.userAccount.id = ?1")
	Company findByUserAccountId(int id);
	
	@Query("select avg(a.score) from Audit a join a.position pos join pos.company c where c.id = ?1")
	Double avgScore(int id);

}
