package ch.matfly.suivirecherches.service.impl;

import ch.matfly.suivirecherches.domain.Recherche;
import ch.matfly.suivirecherches.repository.RechercheRepository;
import ch.matfly.suivirecherches.service.RechercheService;
import ch.matfly.suivirecherches.service.dto.RechercheDTO;
import ch.matfly.suivirecherches.service.mapper.RechercheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Recherche}.
 */
@Service
@Transactional
public class RechercheServiceImpl implements RechercheService {

    private final Logger log = LoggerFactory.getLogger(RechercheServiceImpl.class);

    private final RechercheRepository rechercheRepository;

    private final RechercheMapper rechercheMapper;

    public RechercheServiceImpl(RechercheRepository rechercheRepository, RechercheMapper rechercheMapper) {
        this.rechercheRepository = rechercheRepository;
        this.rechercheMapper = rechercheMapper;
    }

    /**
     * Save a recherche.
     *
     * @param rechercheDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RechercheDTO save(RechercheDTO rechercheDTO) {
        log.debug("Request to save Recherche : {}", rechercheDTO);
        Recherche recherche = rechercheMapper.toEntity(rechercheDTO);
        recherche = rechercheRepository.save(recherche);
        return rechercheMapper.toDto(recherche);
    }

    /**
     * Get all the recherches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RechercheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Recherches");
        return rechercheRepository.findByUserIsCurrentUser(pageable)
            .map(rechercheMapper::toDto);
    }

    /**
     * Get one recherche by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RechercheDTO> findOne(Long id) {
        log.debug("Request to get Recherche : {}", id);
        return rechercheRepository.findById(id)
            .map(rechercheMapper::toDto);
    }

    /**
     * Delete the recherche by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recherche : {}", id);
        rechercheRepository.deleteById(id);
    }
}
