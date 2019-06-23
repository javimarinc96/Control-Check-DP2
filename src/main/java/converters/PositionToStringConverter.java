
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Position;

@Component
@Transactional
public class PositionToStringConverter extends EntityToString<Position> {

}
