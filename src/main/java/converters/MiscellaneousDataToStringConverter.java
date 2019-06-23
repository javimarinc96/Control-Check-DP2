
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MiscellaneousData;

@Component
@Transactional
public class MiscellaneousDataToStringConverter extends EntityToString<MiscellaneousData> {

}
