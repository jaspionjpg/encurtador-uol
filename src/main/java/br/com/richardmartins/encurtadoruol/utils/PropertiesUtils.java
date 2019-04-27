package br.com.richardmartins.encurtadoruol.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesUtils {

	public static String URL_DOMINIO_APLICACAO;

    @Value("${url_dominio_aplicacao}")
    public void setUrlDominioAplicacao(String urlDominioAplicacao) {
    	PropertiesUtils.URL_DOMINIO_APLICACAO = urlDominioAplicacao;
    }
}
