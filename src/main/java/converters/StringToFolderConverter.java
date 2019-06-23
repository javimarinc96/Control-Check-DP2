
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.FolderRepository;
import domain.Folder;

@Component
@Transactional
public class StringToFolderConverter extends StringToEntity<Folder, FolderRepository> {

}
