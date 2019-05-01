package br.com.richardmartins.encurtadoruol.endpoints;

import br.com.richardmartins.encurtadoruol.EncurtadorUolApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EncurtadorUolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class EncurtadorRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;
    private String token;

    private static final String CLIENT_ID = "encurtador_url";
    private static final String CLIENT_SECRET = "secreto";

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        token = pegarAccessToken("uol", "123");
    }

    private String pegarAccessToken(String usuario, String senha) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", usuario);
        params.add("password", senha);

        ResultActions result = mockMvc
                .perform(post("/oauth/token").params(params).with(httpBasic(CLIENT_ID, CLIENT_SECRET)))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();
        return new JacksonJsonParser().parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void buscarLinkStatus200() throws Exception {
        String referencia = "MA";
        mockMvc.perform(get("/api/" + referencia).header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarLinkStatus400() throws Exception {
        String referencia = "MAA";
        mockMvc.perform(get("/api/" + referencia).header("Authorization", "Bearer " + this.token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void criarUrlEncurtadaEBuscar() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/encurtar")
                .header("Authorization", "Bearer " + this.token)
                .param("url", "richardmartins.com.br"))
                .andExpect(status().isCreated());

        String resultString = result.andReturn().getResponse().getContentAsString();
        String referencia = new JacksonJsonParser().parseMap(resultString).get("referenciaUrlGerada").toString();

        mockMvc.perform(get("/api/" + referencia).header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk());
    }
}
