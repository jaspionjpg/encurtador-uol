package br.com.richardmartins.encurtadoruol.repositories.queries;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.richardmartins.encurtadoruol.vo.EstatisticaVO;
import br.com.richardmartins.encurtadoruol.vo.LinkVO;

public interface LinkQuery {
	LinkVO buscarLinkVOPorReferencia(String referencia) throws NoResultException;
	
	String buscarUrlPorReferencia(String referencia) throws NoResultException;
	
	List<EstatisticaVO> buscarInformacoesEstatisticas();
}
