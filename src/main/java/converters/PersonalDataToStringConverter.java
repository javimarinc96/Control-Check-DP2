
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PersonalData;

@Component
@Transactional
public class PersonalDataToStringConverter extends EntityToString<PersonalData> {

}
