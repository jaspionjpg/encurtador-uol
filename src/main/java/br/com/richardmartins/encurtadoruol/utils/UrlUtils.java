package br.com.richardmartins.encurtadoruol.utils;

import java.util.Base64;

public class UrlUtils {

	public static String encurtar(String url) {
		return Base64.getEncoder().encodeToString(url.getBytes());
	}
}
