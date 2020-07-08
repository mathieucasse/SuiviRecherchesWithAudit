package ch.matfly.suivirecherches.repository;

import ch.matfly.suivirecherches.domain.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Entreprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Page<Entreprise> findAllByOrderByName(Pageable pageable);
}
