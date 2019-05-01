package br.com.richardmartins.encurtadoruol.repositories;

import br.com.richardmartins.encurtadoruol.models.Link;
import br.com.richardmartins.encurtadoruol.repositories.queries.LinkQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long>, LinkQuery {

    @Transactional
    @Modifying
    @Query(value = "UPDATE encurtador_uol.link SET numero_vezes_redirecionado = numero_vezes_redirecionado + 1 WHERE referencia_url_gerada = :referencia", nativeQuery = true)
    void somarLinkRedirecionado(@Param("referencia") String referencia);

    Optional<Link> findByReferenciaUrlGerada(String referencia);
}
