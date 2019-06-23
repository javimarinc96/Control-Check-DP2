
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

	@Query("select p from Provider p where p.userAccount.username = ?1")
	Provider findByUserName(String username);

	@Query("select p from Provider p where p.userAccount.id = ?1")
	Provider findByUserAccountId(int id);

}
