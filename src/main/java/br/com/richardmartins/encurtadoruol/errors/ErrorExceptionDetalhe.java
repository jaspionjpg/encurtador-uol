package br.com.richardmartins.encurtadoruol.errors;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorExceptionDetalhe {
	public int status;
	
	public String titulo;
	public String detalhe;

	@JsonFormat(pattern="dd/MM/yyyy - HH:mm")
	public Date data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
