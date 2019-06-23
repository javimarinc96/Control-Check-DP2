
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CurriculaRepository;
import domain.Curricula;

@Component
@Transactional
public class StringToCurriculaConverter extends StringToEntity<Curricula, CurriculaRepository> {

}
