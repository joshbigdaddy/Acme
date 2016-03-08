package utilities;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordGenerate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String password="clerk2";
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password=encoder.encodePassword(password, null);
		System.out.println(password);
	}

}
