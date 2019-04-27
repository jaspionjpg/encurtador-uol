package br.com.richardmartins.encurtadoruol.repositories.queries;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import br.com.richardmartins.encurtadoruol.vo.LinkVO;

public class LinkQueryImpl implements LinkQuery {

	@PersistenceContext
	private EntityManager entityManager;

	public LinkVO buscarLinkVOPorReferencia(String referencia) throws NoResultException {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT link.referencia_url_gerada, link.url, link.data_criacao ");
		sql.append("FROM encurtador_uol.link link ");
		sql.append("WHERE link.referencia_url_gerada like :referencia ");

		Query query = entityManager.createNativeQuery(sql.toString(), Tuple.class);

		query.setParameter("referencia", referencia);

		Tuple objeto = (Tuple) query.getSingleResult();

		LinkVO linkVO = new LinkVO();
		linkVO.setDataCriacao(objeto.get("data_criacao", Date.class));
		linkVO.setReferenciaUrlGerada(objeto.get("referencia_url_gerada", String.class));
		linkVO.setUrl(objeto.get("url", String.class));
		linkVO.popularUrlEncurtada();

		return linkVO;
	}
	
	public String buscarUrlPorReferencia(String referencia) throws NoResultException {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT link.url ");
		sql.append("FROM encurtador_uol.link link ");
		sql.append("WHERE link.referencia_url_gerada like :referencia ");

		Query query = entityManager.createNativeQuery(sql.toString(), Tuple.class);

		query.setParameter("referencia", referencia);

		Tuple objeto = (Tuple) query.getSingleResult();

		return objeto.get("url", String.class);
	}
}
