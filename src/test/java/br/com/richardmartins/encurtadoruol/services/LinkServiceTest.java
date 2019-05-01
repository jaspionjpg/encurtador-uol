package br.com.richardmartins.encurtadoruol.services;

import br.com.richardmartins.encurtadoruol.EncurtadorUolApplication;
import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EncurtadorUolApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class LinkServiceTest {

    @Autowired
    private LinkRepository linkRepository;

    @Test(expected = NoResultException.class)
    public void buscarLinkVOPorReferencia() {
        linkRepository.buscarLinkVOPorReferencia("MA");
    }

}
