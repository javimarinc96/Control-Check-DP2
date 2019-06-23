
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import repositories.TestEntityRepository;

import domain.TestEntity;

@Component
@Transactional
public class StringToTestEntityConverter extends StringToEntity<TestEntity, TestEntityRepository> {

}
