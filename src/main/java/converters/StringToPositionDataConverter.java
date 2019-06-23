
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionDataRepository;
import domain.PositionData;

@Component
@Transactional
public class StringToPositionDataConverter extends StringToEntity<PositionData, PositionDataRepository> {

}
