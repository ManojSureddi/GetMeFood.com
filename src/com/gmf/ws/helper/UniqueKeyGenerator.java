package com.gmf.ws.helper;

import java.util.Random;

public class UniqueKeyGenerator {
	public static String generateKey(String key1, String salt) {
		String temp = Encryptor.encryptData(salt + key1 + salt);
		Random rand = new Random();
		int val = rand.nextInt(temp.length() - 6);
		String key = temp.substring(val, val + 6);
		temp = Encryptor.encryptData(salt + key + salt);
		val = rand.nextInt(temp.length() - 6);
		key = temp.substring(val, val + 6);
		return key;
	}
}
