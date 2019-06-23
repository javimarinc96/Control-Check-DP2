
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer> {

	@Query("select c from Curricula c join c.positionsData p where p.id = ?1")
	Curricula findCurriculaByPositionDataId(int positionDataId);

	@Query("select c from Curricula c where c.personalData.id = ?1")
	Curricula findCurriculaByPersonalDataId(int personalDataId);

	@Query("select c from Curricula c where c.miscellaneousData.id = ?1")
	Curricula findCurriculaByMiscellaneousDataId(int miscellaneousDataId);

	@Query("select c from Curricula c join c.educationsData p where p.id = ?1")
	Curricula findCurriculaByEducationDataId(int educationDataId);
}
