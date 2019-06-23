
package repositories;

import domain.Folder;
import domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

	@Query("select f from Folder f where f.owner.id=?1")
	Collection<Folder> findAllFoldersByActor(int actorId);

	@Query("select f from Folder f where f.owner.id=?1 and f.name=?2")
	Folder findFolderByActor(int actorId, String name);

	@Query("select f from Folder f where f.name='Inbox' and f.owner.id=?1")
	Folder findInboxByPrincipal(int actorId);

	@Query("select f from Folder f where f.name='Outbox' and f.owner.id=?1")
	Folder findOutboxByPrincipal(int actorId);

}
