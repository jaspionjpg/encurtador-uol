package br.com.richardmartins.encurtadoruol.endpoints;

import br.com.richardmartins.encurtadoruol.services.LinkService;
import br.com.richardmartins.encurtadoruol.vo.EstatisticaVO;
import br.com.richardmartins.encurtadoruol.vo.LinkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Api que cadastra e busca os link/urls salvos")
public class EncurtadorRestController {

	private final LinkService linkService;

	public EncurtadorRestController(LinkService linkService) {
		this.linkService = linkService;
	}

	@GetMapping("/{referencia}")
	@ApiOperation(value = "Busca um link e retorna para o usuario em JSON")
	public LinkVO buscarLink(@PathVariable String referencia) {
		return linkService.buscarLinkVOPorReferencia(referencia);
	}

	@PostMapping("/encurtar")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Cria um novo link")
	public LinkVO encurtar(@RequestParam String url) {
		return linkService.salvar(url).toVO();
	}

	@GetMapping("/estatisticas")
	@ApiOperation(value = "Retorna informações estatísticas")
	public List<EstatisticaVO> buscarInformacoesEstatisticas() {
		return linkService.buscarInformacoesEstatisticas();
	}
}
