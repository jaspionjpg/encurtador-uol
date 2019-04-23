package br.com.richardmartins.encurtadoruol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.repositories.queries.LinkQuery;

public interface LinkRepository extends JpaRepository<Link, Long>, LinkQuery {

}
