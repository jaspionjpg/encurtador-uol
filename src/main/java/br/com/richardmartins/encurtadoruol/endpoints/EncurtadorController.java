package br.com.richardmartins.encurtadoruol.endpoints;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.services.LinkService;

@RestController
@RequestMapping("/encurtar")
public class EncurtadorController {

	@Autowired
	private LinkService linkService;

	@GetMapping
	public List<String> listar() {
		return Arrays.asList("Coisa 1", "Coisa 2", "Coisa 3");
	}

	@PostMapping
	public Link encurtar(@RequestParam String url) {
		return linkService.salvar(url);
	}

}
