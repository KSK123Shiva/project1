
package com.app.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils {

	public static String generatePassword() {
		//String keyLength = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		//String pwd = RandomStringUtils.random( 15, keyLength );
		//String pwd = RandomStringUtils.random( keyLength);
		int keyLength = 10;
		
		String pwd = RandomStringUtils.randomAlphabetic(keyLength);
		return pwd;
	}

}
