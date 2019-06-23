
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditorRepository;
import domain.Auditor;

@Component
@Transactional
public class StringToAuditorConverter extends StringToEntity<Auditor, AuditorRepository> {

}
