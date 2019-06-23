
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Provider;

@Component
@Transactional
public class ProviderToStringConverter extends EntityToString<Provider> {

}
