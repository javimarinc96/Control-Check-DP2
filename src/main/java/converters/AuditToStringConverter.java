
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Audit;

@Component
@Transactional
public class AuditToStringConverter extends EntityToString<Audit> {
}
