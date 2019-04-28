package br.com.richardmartins.encurtadoruol.vo;

public class EstatisticaVO {

	private String urlRedirecionamento;

	private Long numeroVezesEncurtado;

	private Long numeroVezesRedirecionado;

	public String getUrlRedirecionamento() {
		return urlRedirecionamento;
	}

	public void setUrlRedirecionamento(String urlRedirecionamento) {
		this.urlRedirecionamento = urlRedirecionamento;
	}

	public Long getNumeroVezesEncurtado() {
		return numeroVezesEncurtado;
	}

	public void setNumeroVezesEncurtado(Long numeroVezesEncurtado) {
		this.numeroVezesEncurtado = numeroVezesEncurtado;
	}

	public Long getNumeroVezesRedirecionado() {
		return numeroVezesRedirecionado;
	}

	public void setNumeroVezesRedirecionado(Long numeroVezesRedirecionado) {
		this.numeroVezesRedirecionado = numeroVezesRedirecionado;
	}
}
