
package converters;

import java.net.URLDecoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.CreditCard;

@Transactional
@Component
public class StringToCreditCardConverter implements Converter<String, CreditCard> {

	@Override
	public CreditCard convert(final String text) {
		CreditCard result;
		String parts[];
		if (text == null)
			result = null;
		else
			try {
				parts = text.split("\\|");
				result = new CreditCard();
				result.setBrandName(URLDecoder.decode(parts[0], "UTF-8"));
				result.setHolderName(URLDecoder.decode(parts[0], "UTF-8"));
				result.setNumber(URLDecoder.decode(parts[0], "UTF-8"));
				result.setCvv(Integer.valueOf(URLDecoder.decode(parts[0], "UTF-8")));
				result.setExpiryMonth(Integer.valueOf(URLDecoder.decode(parts[0], "UTF-8")));
				result.setExpiryYear(Integer.valueOf(URLDecoder.decode(parts[0], "UTF-8")));

			} catch (final Throwable oops) {

				throw new RuntimeException(oops);
			}

		return result;
	}
}
