
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionRepository;
import domain.Position;

@Component
@Transactional
public class StringToPositionConverter extends StringToEntity<Position, PositionRepository> {

}
