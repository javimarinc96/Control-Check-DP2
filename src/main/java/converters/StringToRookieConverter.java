
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RookieRepository;
import domain.Rookie;

@Component
@Transactional
public class StringToRookieConverter extends StringToEntity<Rookie, RookieRepository> {

}
