
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Configurations;

@Component
@Transactional
public class ConfigurationsToStringConverter extends EntityToString<Configurations> {

}
