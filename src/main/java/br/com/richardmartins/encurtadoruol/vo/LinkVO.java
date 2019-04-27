package br.com.richardmartins.encurtadoruol.vo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.richardmartins.encurtadoruol.utils.PropertiesUtils;

public class LinkVO {

	private String url;
	private String urlEncurtada;
	private String referenciaUrlGerada;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataCriacao;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}

	public String getReferenciaUrlGerada() {
		return referenciaUrlGerada;
	}

	public void setReferenciaUrlGerada(String referenciaUrlGerada) {
		this.referenciaUrlGerada = referenciaUrlGerada;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public void popularUrlEncurtada() {
		this.urlEncurtada = StringUtils.join(PropertiesUtils.URL_DOMINIO_APLICACAO, "/", this.referenciaUrlGerada);
	}
}
