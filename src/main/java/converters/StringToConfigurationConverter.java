
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConfigurationsRepository;
import domain.Configurations;

@Component
@Transactional
public class StringToConfigurationConverter extends StringToEntity<Configurations, ConfigurationsRepository> {

}
