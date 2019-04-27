package br.com.richardmartins.encurtadoruol.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.richardmartins.encurtadoruol.vo.LinkVO;

@Entity
@Table(name = "LINK")
public class Link {

	@Id
	@GenericGenerator(name = "linkSequenceGenerator", parameters = @Parameter(name = "sequence_name", value = "LINK_SEQUENCE"), strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
	@GeneratedValue(generator = "linkSequenceGenerator")
	@Column(name = "ID")
	private Long id;

	@Column(name = "url")
	private String url;

	@Column(name = "referencia_url_gerada")
	private String referenciaUrlGerada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public LinkVO toVO() {
		LinkVO linkVO = new LinkVO();
		linkVO.setDataCriacao(this.dataCriacao);
		linkVO.setReferenciaUrlGerada(this.referenciaUrlGerada);
		linkVO.setUrl(this.url);
		linkVO.popularUrlEncurtada();
		return linkVO;
	}
}
