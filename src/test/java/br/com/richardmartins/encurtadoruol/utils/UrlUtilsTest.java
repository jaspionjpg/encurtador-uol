package br.com.richardmartins.encurtadoruol.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlUtilsTest {

	private List<String> urlsValidas;
	private List<String> urlInvalidas;

	@Before
	public void setup() {
		urlsValidas = new ArrayList<>();
		urlsValidas.add("www.google.com.br");
		urlsValidas.add("richardmartins.com.br");
		urlsValidas.add("uol.com.br");
		urlsValidas.add("https://github.com/jaspionjpg/encurtador-uol");

		urlInvalidas = new ArrayList<>();
		urlInvalidas.add("asdfasdfdas");
		urlInvalidas.add("http://goo gle.com");
	}

	@Test
	public void testeUrlsValidas() {
		urlsValidas.forEach(url -> {
			Assert.assertTrue(UrlUtils.validarUrl(url));
		});
	}
	
	@Test
	public void testeUrlsInvalidas() {
		urlInvalidas.forEach(url -> {
			Assert.assertFalse(UrlUtils.validarUrl(url));
		});
	}
}
