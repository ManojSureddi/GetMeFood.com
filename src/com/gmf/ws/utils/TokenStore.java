package com.gmf.ws.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class TokenStore {
	public static TokenStore instance = null;
	public HashMap<String, HashMap<String, HashMap<String, String>>> cart;
	public HashMap<String, String> userTokens;

	private TokenStore() {

		cart = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		userTokens = new HashMap<String, String>();
	}

	public static TokenStore getInstance() {
		if (instance == null) {
			instance = new TokenStore();
		}
		return instance;
	}

	public String getToken(String userId) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		Random rand = new Random();
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < 25; i++) {
			buff.append((char) (rand.nextInt(25) + 97));
			buff.append(userId);
		}
		String text = buff.toString();
		byte[] hash = digest.digest(text.getBytes("UTF-8"));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();

	}
}
