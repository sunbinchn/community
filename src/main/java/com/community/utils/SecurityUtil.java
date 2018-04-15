package com.community.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 密码加密..
 * @author SUNBIN
 *
 */
public class SecurityUtil {
	public static String md5(String str){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(str.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}
}
