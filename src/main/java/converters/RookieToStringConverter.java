
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rookie;

@Component
@Transactional
public class RookieToStringConverter extends EntityToString<Rookie> {

}
