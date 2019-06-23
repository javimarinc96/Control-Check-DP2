
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Administrator;
import domain.Company;
import domain.Provider;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select admin from Administrator admin where admin.userAccount.id = ?1")
	Administrator findByUserAccountId(int id);
	
	@Query("select a from Actor a where a.isSpammer = true")
	Collection<Actor> findSpammers();

	@Query("select c from Company c")
	Collection<Company> getCompaniesWithMorePositions();

	@Query("select avg(a.score) from Audit a")
	Double avgAuditScore();

	@Query("select max(a.score) from Audit a")
	int maxAuditScore();

	@Query("select min(a.score) from Audit a")
	int minAuditScore();

	@Query("select min(1.0 * (select avg(0.1 * (select avg(a.score) from Audit a where a.position.id = p.id and a.position.company.id = c.id)) from Position p)) from Company c")
	double getMinimumScoreCompany();

	@Query("select max(1.0 * (select avg(0.1 * (select avg(a.score) from Audit a where a.position.id = p.id and a.position.company.id = c.id)) from Position p)) from Company c")
	double getMaximumScoreCompany();

	@Query("select avg(1.0 * (select avg(0.1 * (select avg(a.score) from Audit a where a.position.id = p.id and a.position.company.id = c.id)) from Position p)) from Company c")
	double getAverageScoreCompany();

	@Query("select stddev(1.0 * (select avg(0.1 * (select avg(a.score) from Audit a where a.position.id = p.id and a.position.company.id = c.id)) from Position p)) from Company c")
	double getStandardDeviationScoreCompany();

	@Query("select stddev(a.score) from Audit a")
	Double stddevAuditScore();
	//-------------------------------------------------------------------------

	@Query("select avg(1.0*(select count(i.provider) from Item i where i.provider.id=p.id)) from Provider p")
	Double avgItemsProvider();

	@Query("select min(1.0*(select count(i.provider) from Item i where i.provider.id=p.id)) from Provider p")
	Double minItemsProvider();

	@Query("select max(1.0*(select count(i.provider) from Item i where i.provider.id=p.id)) from Provider p")
	Double maxItemsProvider();

	@Query("select stddev(1.0*(select count(i.provider) from Item i where i.provider.id=p.id)) from Provider p")
	Double stddevItemsProvider();
	//-----------------------------------------------------------

	@Query("select avg(h.curriculas.size) from Rookie h")
	Double avgCurriculas();

	@Query("select max(h.curriculas.size) from Rookie h")
	int maxCurriculas();

	@Query("select min(h.curriculas.size) from Rookie h")
	int minCurriculas();

	@Query("select stddev(h.curriculas.size) from Rookie h")
	Double stddevCurriculas();

	@Query("select avg(f.positions.size) from Finder f")
	Double avgResults();

	@Query("select max(f.positions.size) from Finder f")
	int maxResults();

	@Query("select min(f.positions.size) from Finder f")
	int minResults();

	@Query("select stddev(f.positions.size) from Finder f")
	Double stddevResults();

	@Query("select 1.0*count(f)/(select count(f) from Finder f where f.positions.size = 0) from Finder f where f.positions.size > 0")
	Double ratioFinders();

	@Query("select max(1.0*(select count(p) from Position p where p.company.id = c.id)) from Company c")
	Double maxPositions();

	@Query("select avg(1.0*(select count(p) from Position p where p.company.id = c.id)) from Company c")
	Double avgPositions();

	@Query("select min(1.0*(select count(p) from Position p where p.company.id = c.id)) from Company c")
	Double minPositions();

	@Query("select stddev(1.0*(select count(p) from Position p where p.company.id = c.id)) from Company c")
	Double stddevPositions();

	//Administrator applications per rookie dashboard data

	@Query("select max(1.0*(select count(a) from Application a where a.rookie.id = h.id)) from Rookie h")
	Double maxAppRookie();

	@Query("select min(1.0*(select count(a) from Application a where a.rookie.id = h.id)) from Rookie h")
	Double minAppRookie();

	@Query("select avg(1.0*(select count(a) from Application a where a.rookie.id = h.id)) from Rookie h")
	Double avgAppRookie();

	@Query("select stddev(1.0*(select count(a) from Application a where a.rookie.id = h.id)) from Rookie h")
	Double stddevAppRookie();

	//	//Best and worst positions in terms of salary
	//	@Query("select p from Position p where p.salaryOffered = max(p.salaryOffered) from Position")
	
	//-----------NIVEL A--------------
	
	@Query("select avg(1.0*(select count(s) from Sponsorship s where s.provider.id = p.id)) from Provider p")
	double avgSponsorshipProvider();
	
	@Query("select max(1.0*(select count(s) from Sponsorship s where s.provider.id = p.id)) from Provider p")
	int maxSponsorshipProvider();
	
	@Query("select min(1.0*(select count(s) from Sponsorship s where s.provider.id = p.id)) from Provider p")
	int minSponsorshipProvider();
	
	@Query("select stddev(1.0*(select count(s) from Sponsorship s where s.provider.id = p.id)) from Provider p")
	double stddevSponsorshipProvider();
	
	@Query("select avg(1.0*(select count(s) from Sponsorship s where s.position.id = p.id)) from Position p")
	double avgSponsorshipPosition();
	
	@Query("select max(1.0*(select count(s) from Sponsorship s where s.position.id = p.id)) from Position p")
	int maxSponsorshipPosition();
	
	@Query("select min(1.0*(select count(s) from Sponsorship s where s.position.id = p.id)) from Position p")
	int minSponsorshipPosition();
	
	@Query("select stddev(1.0*(select count(s) from Sponsorship s where s.position.id = p.id)) from Position p")
	double stddevSponsorshipPosition();
	
	@Query("select p from Provider p where (select count(s) from Sponsorship s where s.provider.id = p.id) > (select avg(0.15*(select count(s) from Sponsorship s where s.provider.id = p.id)) from Provider p)")
	Collection<Provider> aboveAvgSponsorships();
	

}
