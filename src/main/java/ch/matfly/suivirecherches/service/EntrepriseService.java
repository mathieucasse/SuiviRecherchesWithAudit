package ch.matfly.suivirecherches.service;

import ch.matfly.suivirecherches.service.dto.EntrepriseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ch.matfly.suivirecherches.domain.Entreprise}.
 */
public interface EntrepriseService {

    /**
     * Save a entreprise.
     *
     * @param entrepriseDTO the entity to save.
     * @return the persisted entity.
     */
    EntrepriseDTO save(EntrepriseDTO entrepriseDTO);

    /**
     * Get all the entreprises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntrepriseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" entreprise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntrepriseDTO> findOne(Long id);

    /**
     * Delete the "id" entreprise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
