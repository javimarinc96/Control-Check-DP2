
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Item;

@Component
@Transactional
public class ItemToStringConverter extends EntityToString<Item> {

}
