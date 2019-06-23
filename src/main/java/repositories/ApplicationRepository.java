
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a join a.rookie h where h.id = ?1")
	public Collection<Application> getApplicationsByRookie(int rookieId);

	@Query("select a from Application a join a.position p join p.company c where c.id = ?1")
	public Collection<Application> getApplicationsByCompany(int companyId);

}
