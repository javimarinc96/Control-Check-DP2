
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialProfileRepository;
import domain.SocialProfile;

@Component
@Transactional
public class StringToSocialProfileConverter extends StringToEntity<SocialProfile, SocialProfileRepository> {

}
