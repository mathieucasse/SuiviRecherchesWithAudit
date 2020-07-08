package ch.matfly.suivirecherches.repository;

import ch.matfly.suivirecherches.domain.Recherche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Recherche entity.
 */

@Repository
public interface RechercheRepository extends JpaRepository<Recherche, Long> {

    @Query("select recherche from Recherche recherche where recherche.user.login = ?#{principal.username} order by recherche.resoffredeservice, recherche.date desc")
    Page<Recherche> findByUserIsCurrentUser(Pageable pageable);

    Page<Recherche> findAllByOrderByResoffredeserviceAscDateDesc(Pageable pageable);
}
