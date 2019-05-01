package br.com.richardmartins.encurtadoruol.endpoints;

import br.com.richardmartins.encurtadoruol.services.LinkService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
