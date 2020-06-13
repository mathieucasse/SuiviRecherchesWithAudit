package ch.matfly.suivirecherches.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntrepriseMapperTest {

    private EntrepriseMapper entrepriseMapper;

    @BeforeEach
    public void setUp() {
        entrepriseMapper = new EntrepriseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(entrepriseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entrepriseMapper.fromId(null)).isNull();
    }
}
