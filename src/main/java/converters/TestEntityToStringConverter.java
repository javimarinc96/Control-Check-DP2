
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import domain.TestEntity;

@Component
@Transactional
public class TestEntityToStringConverter extends EntityToString<TestEntity> {

}
