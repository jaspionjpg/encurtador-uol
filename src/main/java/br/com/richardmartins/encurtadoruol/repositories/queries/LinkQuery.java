package br.com.richardmartins.encurtadoruol.repositories.queries;

import javax.persistence.NoResultException;

import br.com.richardmartins.encurtadoruol.vo.LinkVO;

public interface LinkQuery {
	LinkVO buscarLinkVOPorReferencia(String referencia) throws NoResultException;
	
	String buscarUrlPorReferencia(String referencia) throws NoResultException;
}
