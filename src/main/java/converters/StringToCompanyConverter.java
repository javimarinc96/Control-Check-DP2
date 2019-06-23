
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CompanyRepository;
import domain.Company;

@Component
@Transactional
public class StringToCompanyConverter extends StringToEntity<Company, CompanyRepository> {

}
