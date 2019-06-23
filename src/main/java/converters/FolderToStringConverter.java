
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Folder;

@Component
@Transactional
public class FolderToStringConverter extends EntityToString<Folder> {

}
