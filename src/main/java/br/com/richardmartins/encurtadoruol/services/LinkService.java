package br.com.richardmartins.encurtadoruol.services;

import java.util.Date;

import org.springframework.stereotype.Service;

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
		
		String reeferrncia = UrlUtils.INSTANCE.createUniqueID(id);
		
		Link link = new Link();
		link.setUrl(url);
		link.setDataCriacao(new Date());
		link.setReferenciaUrlGerada(reeferrncia);

		return linkRepository.save(link);
	}

	public LinkVO buscarLinkVOPorReferencia(String referencia) {
		return linkRepository.buscarLinkVOPorReferencia(referencia);
	}

	public String buscarUrlPorReferencia(String referencia) {
		return linkRepository.buscarUrlPorReferencia(referencia);
	}
}
