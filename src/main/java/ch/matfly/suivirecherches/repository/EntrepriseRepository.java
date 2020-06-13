package ch.matfly.suivirecherches.repository;

import ch.matfly.suivirecherches.domain.Entreprise;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Entreprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
