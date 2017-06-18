package com.gmf.ws.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
	public static String encryptData(String input) {
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] output = "".getBytes();
			md.update(input.getBytes());
			output = md.digest();
			result = bytesToHex(output);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String bytesToHex(byte[] b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
}
