
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;


@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	@Query("select a from Audit a join a.auditor aud where aud.id = ?1")
	public Collection<Audit> getAuditsByAuditor(int auditorId);

	@Query("select a from Audit a join a.position pos where pos.id = ?1")
	public Collection<Audit> getAuditsByPosition(int positionId);
	
	@Query("select a from Audit a join a.position pos join pos.company c where c.id = ?1")
	public Collection<Audit> getAuditsByCompany(int companyId);

}
