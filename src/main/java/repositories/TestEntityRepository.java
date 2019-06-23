
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.TestEntity;


@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Integer> {

//	@Query("select t from TestEntity t join t.relation1 r1 where r1.id = ?1")
//	public Collection<TestEntity> getTestEntitysByRelationEntity1(int relation1Id);
//
//	@Query("select t from TestEntity t join t.relation2 r2 where r2.id = ?1")
//	public Collection<TestEntity> getTestEntitysByRelationEntity2(int relation2Id);

}
