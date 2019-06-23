
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Auditor;

@Component
@Transactional
public class AuditorToStringConverter extends EntityToString<Auditor> {
}
