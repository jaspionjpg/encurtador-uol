package br.com.richardmartins.encurtadoruol.endpoints;

import br.com.richardmartins.encurtadoruol.services.LinkService;
import br.com.richardmartins.encurtadoruol.vo.EstatisticaVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RedirectControllerTest extends AbstractControllerTest {

    @Autowired
    private LinkService linkService;

    private String referenciaUrlValida;

    @Before
    public void setup() throws Exception {
        this.referenciaUrlValida = this.linkService.salvar("richardmartins.com.br").getReferenciaUrlGerada();
    }

    @Test
    public void buscarLinkRedirecionamento200() throws Exception {
        super.mockMvc.perform(get("/e/" + referenciaUrlValida))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("richardmartins.com.br"));
    }

    @Test
    public void buscarLinkRedirecionamento404() throws Exception {
        String referencia = RandomStringUtils.random(9);
        super.mockMvc.perform(get("/e/" + referencia))
                .andExpect(status().isNotFound());
    }

    @Test
    public void buscarLinkRedirecionamentoESomaEstatistica200() throws Exception {
        this.linkService.salvar("richardmartins.com.br").getReferenciaUrlGerada();

        List<EstatisticaVO> estatisticaVOS = linkService.buscarInformacoesEstatisticas();

        EstatisticaVO estatisticaVO = estatisticaVOS.get(0);

        super.mockMvc.perform(get("/e/" + referenciaUrlValida))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("richardmartins.com.br"));


        List<EstatisticaVO> estatisticaVOSDepoisRedirecionamento = linkService.buscarInformacoesEstatisticas();

        EstatisticaVO estatisticaVODepoisRedirecionamento = estatisticaVOSDepoisRedirecionamento.get(0);

        Assert.assertEquals((Long) (estatisticaVO.getNumeroVezesRedirecionado() + 1), estatisticaVODepoisRedirecionamento.getNumeroVezesRedirecionado());
    }

}
