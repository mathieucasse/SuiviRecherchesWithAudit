package ch.matfly.suivirecherches.service.impl;

import ch.matfly.suivirecherches.service.PersonneService;
import ch.matfly.suivirecherches.domain.Personne;
import ch.matfly.suivirecherches.repository.PersonneRepository;
import ch.matfly.suivirecherches.service.dto.PersonneDTO;
import ch.matfly.suivirecherches.service.mapper.PersonneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Personne}.
 */
@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {

    private final Logger log = LoggerFactory.getLogger(PersonneServiceImpl.class);

    private final PersonneRepository personneRepository;

    private final PersonneMapper personneMapper;

    public PersonneServiceImpl(PersonneRepository personneRepository, PersonneMapper personneMapper) {
        this.personneRepository = personneRepository;
        this.personneMapper = personneMapper;
    }

    /**
     * Save a personne.
     *
     * @param personneDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PersonneDTO save(PersonneDTO personneDTO) {
        log.debug("Request to save Personne : {}", personneDTO);
        Personne personne = personneMapper.toEntity(personneDTO);
        personne = personneRepository.save(personne);
        return personneMapper.toDto(personne);
    }

    /**
     * Get all the personnes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PersonneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Personnes");
        return personneRepository.findAll(pageable)
            .map(personneMapper::toDto);
    }

    /**
     * Get one personne by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonneDTO> findOne(Long id) {
        log.debug("Request to get Personne : {}", id);
        return personneRepository.findById(id)
            .map(personneMapper::toDto);
    }

    /**
     * Delete the personne by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Personne : {}", id);
        personneRepository.deleteById(id);
    }
}
