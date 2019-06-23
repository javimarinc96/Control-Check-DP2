
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Application;

@Component
@Transactional
public class ApplicationToStringConverter extends EntityToString<Application> {
}
