package br.com.richardmartins.encurtadoruol.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import br.com.richardmartins.encurtadoruol.services.LinkService;

@Controller
public class RedirectController {

	private final LinkService linkService;

	public RedirectController(LinkService linkService) {
		this.linkService = linkService;
	}

	@GetMapping("/e/{referencia}")
	public RedirectView buscarLink(@PathVariable String referencia) {
		String url = linkService.buscarUrlPorReferencia(referencia, true);
		return new RedirectView(url);
	}
}
