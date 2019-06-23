
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EducationDataRepository;
import domain.EducationData;

@Component
@Transactional
public class StringToEducationDataConverter extends StringToEntity<EducationData, EducationDataRepository> {

}
