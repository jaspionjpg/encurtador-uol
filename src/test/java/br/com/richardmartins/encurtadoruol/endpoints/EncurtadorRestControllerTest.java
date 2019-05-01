package br.com.richardmartins.encurtadoruol.endpoints;

import br.com.richardmartins.encurtadoruol.services.LinkService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EncurtadorRestControllerTest extends AbstractControllerTest {

    private String token;

    @Autowired
    private LinkService linkService;

    private String referenciaUrlValida;

    @Before
    public void setup() throws Exception {
        this.token = super.pegarAccessToken("uol", "123");

        this.referenciaUrlValida = this.linkService.salvar("richardmartins.com.br").getReferenciaUrlGerada();
    }

    @Test
    public void buscarLinkStatus200() throws Exception {
        super.mockMvc.perform(get("/api/" + referenciaUrlValida)
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarLinkStatus404() throws Exception {
        String referencia = RandomStringUtils.random(9);
        super.mockMvc.perform(get("/api/" + referencia)
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void criarUrlEncurtadaEBuscar200() throws Exception {
        ResultActions result = super.mockMvc.perform(post("/api/encurtar")
                .header("Authorization", "Bearer " + this.token)
                .param("url", "richardmartins.com.br"))
                .andExpect(status().isCreated());

        String resultString = result.andReturn().getResponse().getContentAsString();
        String referencia = new JacksonJsonParser().parseMap(resultString).get("referenciaUrlGerada").toString();

        super.mockMvc.perform(get("/api/" + referencia).header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk());
    }

    @Test
    public void criarUrlEncurtada400() throws Exception {
        super.mockMvc.perform(post("/api/encurtar")
                .header("Authorization", "Bearer " + this.token)
                .param("url", "richard martins.com.br"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void buscarInformacoesEstatisticas200() throws Exception {
        this.linkService.salvar("richardmartins.com.br").getReferenciaUrlGerada();
        super.mockMvc.perform(get("/api/estatisticas")
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk());
    }
}
