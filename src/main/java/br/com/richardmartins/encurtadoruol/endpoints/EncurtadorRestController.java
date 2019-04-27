package br.com.richardmartins.encurtadoruol.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.richardmartins.encurtadoruol.services.LinkService;
import br.com.richardmartins.encurtadoruol.vo.LinkVO;

@RestController
@RequestMapping("/api")
public class EncurtadorRestController {

	private final LinkService linkService;

	public EncurtadorRestController(LinkService linkService) {
		this.linkService = linkService;
	}

	@GetMapping("/{referencia}")
	public LinkVO buscarLink(@PathVariable String referencia) {
		return linkService.buscarLinkVOPorReferencia(referencia);
	}

	@PostMapping("/encurtar")
	public LinkVO encurtar(@RequestParam String url) {
		return linkService.salvar(url).toVO();
	}

}
