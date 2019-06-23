
package repositories;

import domain.Folder;
import domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.folder.id=?1")
	Collection<Message> findAllMessagesByFolder(int folderId);

	@Query("select m from Message m where m.folder.id=?1 and ?2 MEMBER OF m.tags")
	Collection<Message> findAllMessagesByFolderAndTag(int folderId, String tag);

}
