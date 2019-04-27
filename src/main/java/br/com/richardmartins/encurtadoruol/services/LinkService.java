package br.com.richardmartins.encurtadoruol.services;

import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import br.com.richardmartins.encurtadoruol.errors.NotFoundException;
import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;
import br.com.richardmartins.encurtadoruol.utils.UrlUtils;
import br.com.richardmartins.encurtadoruol.vo.LinkVO;

@Service
public class LinkService {

	private final LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public Link salvar(String url) {
		Long id = linkRepository.count();

		String referencia = UrlUtils.INSTANCE.createUniqueID(id);

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

	public String buscarUrlPorReferencia(String referencia) {
		String url;

		try {
			url = linkRepository.buscarUrlPorReferencia(referencia);
		} catch (NoResultException e) {
			throw new NotFoundException("Url não encontrada");
		}
		return url;
	}
}
