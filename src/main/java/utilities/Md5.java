
package utilities;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5 {

	public static boolean isValidMD5(final String s) {
		return s.matches("[a-fA-F0-9]{32}");
	}

	public static String encodeMd5(final String line) {
		Md5PasswordEncoder encoder;
		String hash;

		encoder = new Md5PasswordEncoder();
		hash = encoder.encodePassword(line, null);

		return hash;
	}

}
