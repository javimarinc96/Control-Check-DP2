
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Problem;

@Component
@Transactional
public class ProblemToStringConverter extends EntityToString<Problem> {

}
