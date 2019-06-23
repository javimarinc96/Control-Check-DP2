
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ItemRepository;
import domain.Item;

@Component
@Transactional
public class StringToItemConverter extends StringToEntity<Item, ItemRepository> {

}
