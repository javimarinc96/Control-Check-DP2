
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Curricula;

@Component
@Transactional
public class CurriculaToStringConverter extends EntityToString<Curricula> {

}
