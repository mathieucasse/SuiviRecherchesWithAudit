package ch.matfly.suivirecherches.repository;

import ch.matfly.suivirecherches.domain.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Personne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    Page<Personne> findAllByOrderByLastName(Pageable pageable);
}
