/**
 * 
 */
package com.kaka.common;

import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * This is the class used to encode the password and security answer.
 * @author COBS Developing Group
 *
 */
public class MD5 {

	public static String encrypt(String strInput) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(strInput == null) {
			return strInput;
		} else {
			MessageDigest md5 = null;
			
			try {
				md5 = MessageDigest.getInstance("MD5");
				
				sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
				
				String strNew = encoder.encode(md5.digest(strInput.getBytes("utf-8")));
				
				return strNew;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;	
	}
	
	public static String encoding(String strInput) {
		return strInput.replaceAll("'", "''");
	}
}
