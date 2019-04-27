package br.com.richardmartins.encurtadoruol.repositories.queries;

import br.com.richardmartins.encurtadoruol.vo.LinkVO;

public interface LinkQuery {
	LinkVO buscarLinkVOPorReferencia(String referencia);
	
	String buscarUrlPorReferencia(String referencia);
}
