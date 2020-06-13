package ch.matfly.suivirecherches.web.rest;

import ch.matfly.suivirecherches.service.RechercheService;
import ch.matfly.suivirecherches.web.rest.errors.BadRequestAlertException;
import ch.matfly.suivirecherches.service.dto.RechercheDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ch.matfly.suivirecherches.domain.Recherche}.
 */
@RestController
@RequestMapping("/api")
public class RechercheResource {

    private final Logger log = LoggerFactory.getLogger(RechercheResource.class);

    private static final String ENTITY_NAME = "recherche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RechercheService rechercheService;

    public RechercheResource(RechercheService rechercheService) {
        this.rechercheService = rechercheService;
    }

    /**
     * {@code POST  /recherches} : Create a new recherche.
     *
     * @param rechercheDTO the rechercheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rechercheDTO, or with status {@code 400 (Bad Request)} if the recherche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recherches")
    public ResponseEntity<RechercheDTO> createRecherche(@Valid @RequestBody RechercheDTO rechercheDTO) throws URISyntaxException {
        log.debug("REST request to save Recherche : {}", rechercheDTO);
        if (rechercheDTO.getId() != null) {
            throw new BadRequestAlertException("A new recherche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RechercheDTO result = rechercheService.save(rechercheDTO);
        return ResponseEntity.created(new URI("/api/recherches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recherches} : Updates an existing recherche.
     *
     * @param rechercheDTO the rechercheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rechercheDTO,
     * or with status {@code 400 (Bad Request)} if the rechercheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rechercheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recherches")
    public ResponseEntity<RechercheDTO> updateRecherche(@Valid @RequestBody RechercheDTO rechercheDTO) throws URISyntaxException {
        log.debug("REST request to update Recherche : {}", rechercheDTO);
        if (rechercheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RechercheDTO result = rechercheService.save(rechercheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rechercheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recherches} : get all the recherches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recherches in body.
     */
    @GetMapping("/recherches")
    public ResponseEntity<List<RechercheDTO>> getAllRecherches(Pageable pageable) {
        log.debug("REST request to get a page of Recherches");
        Page<RechercheDTO> page = rechercheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /recherches/:id} : get the "id" recherche.
     *
     * @param id the id of the rechercheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rechercheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recherches/{id}")
    public ResponseEntity<RechercheDTO> getRecherche(@PathVariable Long id) {
        log.debug("REST request to get Recherche : {}", id);
        Optional<RechercheDTO> rechercheDTO = rechercheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rechercheDTO);
    }

    /**
     * {@code DELETE  /recherches/:id} : delete the "id" recherche.
     *
     * @param id the id of the rechercheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recherches/{id}")
    public ResponseEntity<Void> deleteRecherche(@PathVariable Long id) {
        log.debug("REST request to delete Recherche : {}", id);
        rechercheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
