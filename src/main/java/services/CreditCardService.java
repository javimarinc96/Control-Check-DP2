
package services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreditCardService {

	public boolean validateNumber(final String stringNumber) {

		if (stringNumber.length() != 16)
			return false;
		else {
			final int[] ints = new int[stringNumber.length()];
			for (int i = 0; i < stringNumber.length(); i++)
				ints[i] = Integer.parseInt(stringNumber.substring(i, i + 1));
			for (int i = ints.length - 2; i >= 0; i = i - 2) {
				int j = ints[i];
				j = j * 2;
				if (j > 9)
					j = j % 10 + 1;
				ints[i] = j;
			}
			int res = 0;
			for (int i = 0; i < ints.length; i++)
				res += ints[i];
			if (res % 10 == 0)
				return true;
			else
				return false;
		}
	}

	public boolean validateDate(final int expiryYear, final int expiryMonth) {
		boolean res = true;

		if (expiryYear <= 99)
			res = false;
		else if (expiryMonth <= 19)
			res = false;

		return res;
	}

	public boolean validateCvv(final int cvv) {
		return cvv >= 100 && cvv <= 999;
	}

}
