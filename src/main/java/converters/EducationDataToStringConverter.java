
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.EducationData;

@Component
@Transactional
public class EducationDataToStringConverter extends EntityToString<EducationData> {

}
