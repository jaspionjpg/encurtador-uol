package br.com.richardmartins.encurtadoruol.services;

import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.richardmartins.encurtadoruol.repositories.LinkRepository;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class LinkServiceTest {

	@MockBean
	private LinkRepository linkRepository;

	@InjectMocks
	private LinkService linkService;

	List<String> referencias;

}
