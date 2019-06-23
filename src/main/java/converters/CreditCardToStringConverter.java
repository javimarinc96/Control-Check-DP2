
package converters;

import java.net.URLEncoder;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.CreditCard;

@Transactional
@Component
public class CreditCardToStringConverter implements Converter<CreditCard, String> {

	@Override
	public String convert(final CreditCard creditCard) {
		String result;
		StringBuilder builder;
		if (creditCard == null)
			result = null;
		else {

		}
		try {
			builder = new StringBuilder();
			builder.append(URLEncoder.encode(creditCard.getBrandName().toString(), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(creditCard.getHolderName().toString(), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(creditCard.getNumber().toString(), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(Integer.toString(creditCard.getCvv()), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(Integer.toString(creditCard.getExpiryMonth()), "UTF-8"));
			builder.append("|");
			builder.append(URLEncoder.encode(Integer.toString(creditCard.getExpiryYear()), "UTF-8"));

			result = builder.toString();

		} catch (final Throwable oops) {

			throw new RuntimeException(oops);
		}

		return result;
	}
}
