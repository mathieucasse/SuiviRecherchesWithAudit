package ch.matfly.suivirecherches.web.rest;

import ch.matfly.suivirecherches.SuiviRecherchesApp;
import ch.matfly.suivirecherches.domain.Recherche;
import ch.matfly.suivirecherches.repository.RechercheRepository;
import ch.matfly.suivirecherches.service.RechercheService;
import ch.matfly.suivirecherches.service.dto.RechercheDTO;
import ch.matfly.suivirecherches.service.mapper.RechercheMapper;
import ch.matfly.suivirecherches.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ch.matfly.suivirecherches.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.matfly.suivirecherches.domain.enumeration.OffreDeService;
import ch.matfly.suivirecherches.domain.enumeration.ResOffreDeService;
/**
 * Integration tests for the {@link RechercheResource} REST controller.
 */
@SpringBootTest(classes = SuiviRecherchesApp.class)
public class RechercheResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_POSTE = "AAAAAAAAAA";
    private static final String UPDATED_POSTE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ASSIGNATION_ORP = false;
    private static final Boolean UPDATED_ASSIGNATION_ORP = true;

    private static final Integer DEFAULT_TXACTIVITE = 1;
    private static final Integer UPDATED_TXACTIVITE = 2;

    private static final OffreDeService DEFAULT_OFFREDESERVICE = OffreDeService.Email;
    private static final OffreDeService UPDATED_OFFREDESERVICE = OffreDeService.Visite;

    private static final ResOffreDeService DEFAULT_RESOFFREDESERVICE = ResOffreDeService.EnCours;
    private static final ResOffreDeService UPDATED_RESOFFREDESERVICE = ResOffreDeService.Entretien;

    private static final String DEFAULT_MOTIFRES = "AAAAAAAAAA";
    private static final String UPDATED_MOTIFRES = "BBBBBBBBBB";

    @Autowired
    private RechercheRepository rechercheRepository;

    @Autowired
    private RechercheMapper rechercheMapper;

    @Autowired
    private RechercheService rechercheService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restRechercheMockMvc;

    private Recherche recherche;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RechercheResource rechercheResource = new RechercheResource(rechercheService);
        this.restRechercheMockMvc = MockMvcBuilders.standaloneSetup(rechercheResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recherche createEntity(EntityManager em) {
        Recherche recherche = new Recherche()
            .date(DEFAULT_DATE)
            .poste(DEFAULT_POSTE)
            .location(DEFAULT_LOCATION)
            .assignationORP(DEFAULT_ASSIGNATION_ORP)
            .txactivite(DEFAULT_TXACTIVITE)
            .offredeservice(DEFAULT_OFFREDESERVICE)
            .resoffredeservice(DEFAULT_RESOFFREDESERVICE)
            .motifres(DEFAULT_MOTIFRES);
        return recherche;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recherche createUpdatedEntity(EntityManager em) {
        Recherche recherche = new Recherche()
            .date(UPDATED_DATE)
            .poste(UPDATED_POSTE)
            .location(UPDATED_LOCATION)
            .assignationORP(UPDATED_ASSIGNATION_ORP)
            .txactivite(UPDATED_TXACTIVITE)
            .offredeservice(UPDATED_OFFREDESERVICE)
            .resoffredeservice(UPDATED_RESOFFREDESERVICE)
            .motifres(UPDATED_MOTIFRES);
        return recherche;
    }

    @BeforeEach
    public void initTest() {
        recherche = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecherche() throws Exception {
        int databaseSizeBeforeCreate = rechercheRepository.findAll().size();

        // Create the Recherche
        RechercheDTO rechercheDTO = rechercheMapper.toDto(recherche);
        restRechercheMockMvc.perform(post("/api/recherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rechercheDTO)))
            .andExpect(status().isCreated());

        // Validate the Recherche in the database
        List<Recherche> rechercheList = rechercheRepository.findAll();
        assertThat(rechercheList).hasSize(databaseSizeBeforeCreate + 1);
        Recherche testRecherche = rechercheList.get(rechercheList.size() - 1);
        assertThat(testRecherche.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRecherche.getPoste()).isEqualTo(DEFAULT_POSTE);
        assertThat(testRecherche.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testRecherche.isAssignationORP()).isEqualTo(DEFAULT_ASSIGNATION_ORP);
        assertThat(testRecherche.getTxactivite()).isEqualTo(DEFAULT_TXACTIVITE);
        assertThat(testRecherche.getOffredeservice()).isEqualTo(DEFAULT_OFFREDESERVICE);
        assertThat(testRecherche.getResoffredeservice()).isEqualTo(DEFAULT_RESOFFREDESERVICE);
        assertThat(testRecherche.getMotifres()).isEqualTo(DEFAULT_MOTIFRES);
    }

    @Test
    @Transactional
    public void createRechercheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rechercheRepository.findAll().size();

        // Create the Recherche with an existing ID
        recherche.setId(1L);
        RechercheDTO rechercheDTO = rechercheMapper.toDto(recherche);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRechercheMockMvc.perform(post("/api/recherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rechercheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recherche in the database
        List<Recherche> rechercheList = rechercheRepository.findAll();
        assertThat(rechercheList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRecherches() throws Exception {
        // Initialize the database
        rechercheRepository.saveAndFlush(recherche);

        // Get all the rechercheList
        restRechercheMockMvc.perform(get("/api/recherches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recherche.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].poste").value(hasItem(DEFAULT_POSTE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].assignationORP").value(hasItem(DEFAULT_ASSIGNATION_ORP.booleanValue())))
            .andExpect(jsonPath("$.[*].txactivite").value(hasItem(DEFAULT_TXACTIVITE)))
            .andExpect(jsonPath("$.[*].offredeservice").value(hasItem(DEFAULT_OFFREDESERVICE.toString())))
            .andExpect(jsonPath("$.[*].resoffredeservice").value(hasItem(DEFAULT_RESOFFREDESERVICE.toString())))
            .andExpect(jsonPath("$.[*].motifres").value(hasItem(DEFAULT_MOTIFRES)));
    }
    
    @Test
    @Transactional
    public void getRecherche() throws Exception {
        // Initialize the database
        rechercheRepository.saveAndFlush(recherche);

        // Get the recherche
        restRechercheMockMvc.perform(get("/api/recherches/{id}", recherche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(recherche.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.poste").value(DEFAULT_POSTE))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.assignationORP").value(DEFAULT_ASSIGNATION_ORP.booleanValue()))
            .andExpect(jsonPath("$.txactivite").value(DEFAULT_TXACTIVITE))
            .andExpect(jsonPath("$.offredeservice").value(DEFAULT_OFFREDESERVICE.toString()))
            .andExpect(jsonPath("$.resoffredeservice").value(DEFAULT_RESOFFREDESERVICE.toString()))
            .andExpect(jsonPath("$.motifres").value(DEFAULT_MOTIFRES));
    }

    @Test
    @Transactional
    public void getNonExistingRecherche() throws Exception {
        // Get the recherche
        restRechercheMockMvc.perform(get("/api/recherches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecherche() throws Exception {
        // Initialize the database
        rechercheRepository.saveAndFlush(recherche);

        int databaseSizeBeforeUpdate = rechercheRepository.findAll().size();

        // Update the recherche
        Recherche updatedRecherche = rechercheRepository.findById(recherche.getId()).get();
        // Disconnect from session so that the updates on updatedRecherche are not directly saved in db
        em.detach(updatedRecherche);
        updatedRecherche
            .date(UPDATED_DATE)
            .poste(UPDATED_POSTE)
            .location(UPDATED_LOCATION)
            .assignationORP(UPDATED_ASSIGNATION_ORP)
            .txactivite(UPDATED_TXACTIVITE)
            .offredeservice(UPDATED_OFFREDESERVICE)
            .resoffredeservice(UPDATED_RESOFFREDESERVICE)
            .motifres(UPDATED_MOTIFRES);
        RechercheDTO rechercheDTO = rechercheMapper.toDto(updatedRecherche);

        restRechercheMockMvc.perform(put("/api/recherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rechercheDTO)))
            .andExpect(status().isOk());

        // Validate the Recherche in the database
        List<Recherche> rechercheList = rechercheRepository.findAll();
        assertThat(rechercheList).hasSize(databaseSizeBeforeUpdate);
        Recherche testRecherche = rechercheList.get(rechercheList.size() - 1);
        assertThat(testRecherche.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRecherche.getPoste()).isEqualTo(UPDATED_POSTE);
        assertThat(testRecherche.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testRecherche.isAssignationORP()).isEqualTo(UPDATED_ASSIGNATION_ORP);
        assertThat(testRecherche.getTxactivite()).isEqualTo(UPDATED_TXACTIVITE);
        assertThat(testRecherche.getOffredeservice()).isEqualTo(UPDATED_OFFREDESERVICE);
        assertThat(testRecherche.getResoffredeservice()).isEqualTo(UPDATED_RESOFFREDESERVICE);
        assertThat(testRecherche.getMotifres()).isEqualTo(UPDATED_MOTIFRES);
    }

    @Test
    @Transactional
    public void updateNonExistingRecherche() throws Exception {
        int databaseSizeBeforeUpdate = rechercheRepository.findAll().size();

        // Create the Recherche
        RechercheDTO rechercheDTO = rechercheMapper.toDto(recherche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRechercheMockMvc.perform(put("/api/recherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rechercheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recherche in the database
        List<Recherche> rechercheList = rechercheRepository.findAll();
        assertThat(rechercheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecherche() throws Exception {
        // Initialize the database
        rechercheRepository.saveAndFlush(recherche);

        int databaseSizeBeforeDelete = rechercheRepository.findAll().size();

        // Delete the recherche
        restRechercheMockMvc.perform(delete("/api/recherches/{id}", recherche.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Recherche> rechercheList = rechercheRepository.findAll();
        assertThat(rechercheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
