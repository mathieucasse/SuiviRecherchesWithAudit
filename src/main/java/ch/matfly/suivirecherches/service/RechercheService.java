package ch.matfly.suivirecherches.service;

import ch.matfly.suivirecherches.service.dto.RechercheDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ch.matfly.suivirecherches.domain.Recherche}.
 */
public interface RechercheService {

    /**
     * Save a recherche.
     *
     * @param rechercheDTO the entity to save.
     * @return the persisted entity.
     */
    RechercheDTO save(RechercheDTO rechercheDTO);

    /**
     * Get all the recherches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RechercheDTO> findAll(Pageable pageable);

    /**
     * Get the "id" recherche.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RechercheDTO> findOne(Long id);

    /**
     * Delete the "id" recherche.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
