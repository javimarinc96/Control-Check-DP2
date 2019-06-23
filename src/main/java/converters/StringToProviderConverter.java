
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProviderRepository;
import domain.Provider;

@Component
@Transactional
public class StringToProviderConverter extends StringToEntity<Provider, ProviderRepository> {

}
