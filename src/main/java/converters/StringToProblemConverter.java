
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProblemRepository;
import domain.Problem;

@Component
@Transactional
public class StringToProblemConverter extends StringToEntity<Problem, ProblemRepository> {

}
