package br.com.richardmartins.encurtadoruol.services;

import br.com.richardmartins.encurtadoruol.errors.BadRequestException;
import br.com.richardmartins.encurtadoruol.errors.NotFoundException;
import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;
import br.com.richardmartins.encurtadoruol.utils.UrlUtils;
import br.com.richardmartins.encurtadoruol.vo.EstatisticaVO;
import br.com.richardmartins.encurtadoruol.vo.LinkVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

	private final LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public Link salvar(String url) {
		validaUrl(url);
		Long id = linkRepository.count();

		String referencia = UrlUtils.encurtar(id);

		Link link = new Link();
		link.setUrl(url);
		link.setDataCriacao(new Date());
		link.setReferenciaUrlGerada(referencia);

		return linkRepository.save(link);
	}

	public LinkVO buscarLinkVOPorReferencia(String referencia) {
		LinkVO linkVO;

		try {
			linkVO = linkRepository.buscarLinkVOPorReferencia(referencia);
		} catch (NoResultException e) {
			throw new NotFoundException("Link não encontrado");
		}
		return linkVO;
	}

	public void somarLinkRedirecionado(String referencia) {
		linkRepository.somarLinkRedirecionado(referencia);
	}

	public String buscarUrlPorReferencia(String referencia, Boolean somar) throws BadRequestException {
		String url;

		try {
			url = linkRepository.buscarUrlPorReferencia(referencia);
			if (somar) {
				this.somarLinkRedirecionado(referencia);
			}
		} catch (NoResultException e) {
			throw new NotFoundException("Url não encontrada");
		}
		return url;
	}

	public List<EstatisticaVO> buscarInformacoesEstatisticas() {
		return linkRepository.buscarInformacoesEstatisticas();
	}

	public void validaUrl(String url) {
		if (StringUtils.isBlank(url)) {
			throw new BadRequestException("Deve-se passar uma url que não seja vazia");
		}

		if (!UrlUtils.validarUrl(url)) {
			throw new BadRequestException("Está url não é valida");
		}
	}
}
