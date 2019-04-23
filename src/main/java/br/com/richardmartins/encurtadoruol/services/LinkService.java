package br.com.richardmartins.encurtadoruol.services;

import org.springframework.stereotype.Service;

import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;

@Service
public class LinkService {

	private final LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

}
