package br.com.richardmartins.encurtadoruol.utils;

import java.net.URL;
import java.util.Base64;

public class UrlUtils {

	public static String encurtar(Long count) {
		return Base64.getEncoder().encodeToString(count.toString().getBytes()).replace("=", "");
	}

	public static Boolean validarUrl(String url) {
		if (!url.startsWith("http") && !url.startsWith("https")) {
			url = "http://" + url;
		}

		try {
			new URL(url).openStream().close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
