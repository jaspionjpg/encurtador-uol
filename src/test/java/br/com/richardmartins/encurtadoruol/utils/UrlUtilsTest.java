package br.com.richardmartins.encurtadoruol.utils;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RunWith(SpringRunner.class)
public class UrlUtilsTest {

    @Test
    public void testeUrlsValidas() {
        List<String> urlsValidas = new ArrayList<>();
        urlsValidas.add("www.google.com.br");
        urlsValidas.add("richardmartins.com.br");
        urlsValidas.add("uol.com.br");
        urlsValidas.add("https://github.com/jaspionjpg/encurtador-uol");

        urlsValidas.forEach(url -> {
            Assert.assertTrue(UrlUtils.validarUrl(url));
        });
    }

    @Test
    public void testeUrlsInvalidas() {
        List<String> urlInvalidas = new ArrayList<>();
        urlInvalidas.add("asdfasdfdas");
        urlInvalidas.add("http://goo gle.com");

        urlInvalidas.forEach(url -> {
            Assert.assertFalse(UrlUtils.validarUrl(url));
        });
    }

    @Test
    public void testeEncurtar() {
        Long countLinks = RandomUtils.nextLong(0, 999);

        String encurtar1 = UrlUtils.encurtar(countLinks);
        String encurtar2 = UrlUtils.encurtar(countLinks);

        Assert.assertEquals(encurtar1, encurtar2);

        String s1 = new String(Base64.getDecoder().decode(encurtar1.getBytes()));

        Assert.assertEquals(countLinks.toString(), s1);
    }
}
