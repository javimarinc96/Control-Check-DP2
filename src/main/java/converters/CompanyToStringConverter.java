
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;

@Component
@Transactional
public class CompanyToStringConverter extends EntityToString<Company> {

}
