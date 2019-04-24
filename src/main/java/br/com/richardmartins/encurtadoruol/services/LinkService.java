package br.com.richardmartins.encurtadoruol.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;
import br.com.richardmartins.encurtadoruol.utils.UrlUtils;

@Service
public class LinkService {

	private final LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public Link salvar(String url) {
		Link link = new Link();
		link.setUrl(url);
		link.setDataCriacao(new Date());
		link.setReferenciaUrlGerada(UrlUtils.encurtar(url));
		
		return linkRepository.save(link);
	}

}
