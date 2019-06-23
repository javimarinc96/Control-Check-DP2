
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PersonalDataRepository;
import domain.PersonalData;

@Component
@Transactional
public class StringToPersonalDataConverter extends StringToEntity<PersonalData, PersonalDataRepository> {

}
