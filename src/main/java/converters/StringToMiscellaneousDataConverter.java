
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MiscellaneousDataRepository;
import domain.MiscellaneousData;

@Component
@Transactional
public class StringToMiscellaneousDataConverter extends StringToEntity<MiscellaneousData, MiscellaneousDataRepository> {

}
