package br.com.richardmartins.encurtadoruol.repositories.queries;

import br.com.richardmartins.encurtadoruol.vo.EstatisticaVO;
import br.com.richardmartins.encurtadoruol.vo.LinkVO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LinkQueryImpl implements LinkQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public LinkVO buscarLinkVOPorReferencia(String referencia) throws NoResultException {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT link.referencia_url_gerada, link.url, link.data_criacao ");
        sql.append("FROM encurtador_uol.link link ");
        sql.append("WHERE link.referencia_url_gerada LIKE :referencia ");

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
        sql.append("WHERE link.referencia_url_gerada LIKE :referencia ");

        Query query = entityManager.createNativeQuery(sql.toString(), Tuple.class);

        query.setParameter("referencia", referencia);

        Tuple objeto = (Tuple) query.getSingleResult();

        return objeto.get("url", String.class);
    }

    public List<EstatisticaVO> buscarInformacoesEstatisticas() {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT link.url as url, ");
        sql.append("COUNT(link.url) AS numeroVezesEncurtado, ");
        sql.append("SUM(link.numero_vezes_redirecionado) AS numeroVezesRedirecionado ");
        sql.append("FROM encurtador_uol.link link ");
        sql.append("GROUP BY link.url ORDER BY numeroVezesEncurtado DESC, numeroVezesRedirecionado DESC ");

        Query query = entityManager.createNativeQuery(sql.toString(), Tuple.class);

        List<Tuple> objetos = query.getResultList();

        List<EstatisticaVO> estatisticas = new ArrayList<>();
        for (Tuple objeto : objetos) {
            EstatisticaVO estatisticaVO = new EstatisticaVO();

            estatisticaVO.setUrlRedirecionamento(objeto.get("url", String.class));
            estatisticaVO.setNumeroVezesEncurtado(objeto.get("numeroVezesEncurtado", BigInteger.class).longValue());

            BigDecimal numeroVezesRedirecionado = objeto.get("numeroVezesRedirecionado", BigDecimal.class);
            estatisticaVO.setNumeroVezesRedirecionado(numeroVezesRedirecionado != null ? numeroVezesRedirecionado.longValue() : 0l);

            estatisticas.add(estatisticaVO);
        }

        return estatisticas;
    }
}
