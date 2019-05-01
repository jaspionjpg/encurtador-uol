package br.com.richardmartins.encurtadoruol.services;

import br.com.richardmartins.encurtadoruol.EncurtadorUolApplication;
import br.com.richardmartins.encurtadoruol.errors.BadRequestException;
import br.com.richardmartins.encurtadoruol.errors.NotFoundException;
import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EncurtadorUolApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    @Autowired
    private LinkRepository linkRepository;

    private String referenciaUrlValida;

    @Before
    public void setup() throws Exception {
        this.referenciaUrlValida = this.linkService.salvar("richardmartins.com.br").getReferenciaUrlGerada();
    }

    @Test(expected = BadRequestException.class)
    public void validaUrl400ValorIncorreto() {
        linkService.validaUrl("richard martins.com.br");
    }

    @Test(expected = BadRequestException.class)
    public void validaUrl400ValorVazio() {
        linkService.validaUrl("");
    }

    @Test
    public void validaUrl200() {
        linkService.validaUrl("richardmartins.com.br");
    }

    @Test
    public void salvar() {
        Link link = linkService.salvar("richardmartins.com.br");

        Assert.assertNotNull(linkService.buscarLinkVOPorReferencia(link.getReferenciaUrlGerada()));
    }

    @Test(expected = NotFoundException.class)
    public void buscarLinkVOPorReferencia404() {
        linkService.buscarLinkVOPorReferencia("0");
    }

    @Test
    public void buscarLinkVOPorReferencia200() {
        Assert.assertNotNull(linkService.buscarLinkVOPorReferencia(referenciaUrlValida));
    }

    @Test(expected = NotFoundException.class)
    public void buscarUrlPorReferencia404() {
        linkService.buscarLinkVOPorReferencia("0");
    }

    @Test
    public void buscarUrlPorReferencia200() {
        Assert.assertNotNull(linkService.buscarLinkVOPorReferencia(referenciaUrlValida));
    }

    @Test
    public void somarLinkRedirecionado() {
        Optional<Link> referenciaUrlGeradaOptional = linkRepository.findByReferenciaUrlGerada(referenciaUrlValida);

        referenciaUrlGeradaOptional.ifPresent(link -> {
            linkService.somarLinkRedirecionado(link.getReferenciaUrlGerada());

            Link linkDepoisDaSoma = linkRepository.findByReferenciaUrlGerada(link.getReferenciaUrlGerada()).get();

            Assert.assertEquals((Long) (link.getNumeroVezesRedirecionado() + 1l), linkDepoisDaSoma.getNumeroVezesRedirecionado());
        });


    }

    @Test
    public void buscarInformacoesEstatisticas() {
        Assert.assertNotNull(linkService.buscarInformacoesEstatisticas());
    }
}
