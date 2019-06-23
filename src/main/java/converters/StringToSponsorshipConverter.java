
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Component
@Transactional
public class StringToSponsorshipConverter extends StringToEntity<Sponsorship, SponsorshipRepository> {

}
