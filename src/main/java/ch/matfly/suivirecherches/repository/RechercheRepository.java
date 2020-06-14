package ch.matfly.suivirecherches.repository;

import ch.matfly.suivirecherches.domain.Recherche;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Recherche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RechercheRepository extends JpaRepository<Recherche, Long> {
    Page<Recherche> findAllByOrderByDateDesc(Pageable pageable);
}
