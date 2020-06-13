package ch.matfly.suivirecherches.web.rest;

import ch.matfly.suivirecherches.SuiviRecherchesApp;
import ch.matfly.suivirecherches.domain.Entreprise;
import ch.matfly.suivirecherches.repository.EntrepriseRepository;
import ch.matfly.suivirecherches.service.EntrepriseService;
import ch.matfly.suivirecherches.service.dto.EntrepriseDTO;
import ch.matfly.suivirecherches.service.mapper.EntrepriseMapper;
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
import java.util.List;

import static ch.matfly.suivirecherches.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EntrepriseResource} REST controller.
 */
@SpringBootTest(classes = SuiviRecherchesApp.class)
public class EntrepriseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private EntrepriseMapper entrepriseMapper;

    @Autowired
    private EntrepriseService entrepriseService;

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

    private MockMvc restEntrepriseMockMvc;

    private Entreprise entreprise;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntrepriseResource entrepriseResource = new EntrepriseResource(entrepriseService);
        this.restEntrepriseMockMvc = MockMvcBuilders.standaloneSetup(entrepriseResource)
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
    public static Entreprise createEntity(EntityManager em) {
        Entreprise entreprise = new Entreprise()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return entreprise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entreprise createUpdatedEntity(EntityManager em) {
        Entreprise entreprise = new Entreprise()
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return entreprise;
    }

    @BeforeEach
    public void initTest() {
        entreprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntreprise() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();

        // Create the Entreprise
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);
        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isCreated());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeCreate + 1);
        Entreprise testEntreprise = entrepriseList.get(entrepriseList.size() - 1);
        assertThat(testEntreprise.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEntreprise.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEntreprise.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createEntrepriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();

        // Create the Entreprise with an existing ID
        entreprise.setId(1L);
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntreprises() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get all the entrepriseList
        restEntrepriseMockMvc.perform(get("/api/entreprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entreprise.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", entreprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entreprise.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingEntreprise() throws Exception {
        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // Update the entreprise
        Entreprise updatedEntreprise = entrepriseRepository.findById(entreprise.getId()).get();
        // Disconnect from session so that the updates on updatedEntreprise are not directly saved in db
        em.detach(updatedEntreprise);
        updatedEntreprise
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(updatedEntreprise);

        restEntrepriseMockMvc.perform(put("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isOk());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeUpdate);
        Entreprise testEntreprise = entrepriseList.get(entrepriseList.size() - 1);
        assertThat(testEntreprise.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEntreprise.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEntreprise.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingEntreprise() throws Exception {
        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // Create the Entreprise
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntrepriseMockMvc.perform(put("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        int databaseSizeBeforeDelete = entrepriseRepository.findAll().size();

        // Delete the entreprise
        restEntrepriseMockMvc.perform(delete("/api/entreprises/{id}", entreprise.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
