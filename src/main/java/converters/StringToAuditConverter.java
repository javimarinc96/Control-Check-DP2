
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditRepository;
import domain.Audit;

@Component
@Transactional
public class StringToAuditConverter extends StringToEntity<Audit, AuditRepository> {

}
