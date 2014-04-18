package it.store.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MD5Encoder {
	
	public MD5Encoder() { }
	
	/*
	 * Calcola l'MD5 di una stringa
	 */
	public String encodeString(String str) {
		StringBuilder md5 = new StringBuilder();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(Charset.isSupported("CP1252"))
				md.update(str.getBytes(Charset.forName("CP1252")));
			else
				md.update(str.getBytes(Charset.forName("ISO-8859-1")));

			byte[]bytes = md.digest();
			
			for(int i = 0; i < bytes.length; i++)
				md5.append(Integer.toHexString( ( bytes[i] & 0xFF ) | 0x100 ).substring(1, 3));
		} catch (Exception e) {
			return "error";
		}
		
		System.out.println("MD5 calcolato: "+md5.toString());
		return md5.toString();
	}
	
}
