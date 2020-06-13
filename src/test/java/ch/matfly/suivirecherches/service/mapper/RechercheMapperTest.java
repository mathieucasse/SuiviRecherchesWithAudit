package ch.matfly.suivirecherches.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RechercheMapperTest {

    private RechercheMapper rechercheMapper;

    @BeforeEach
    public void setUp() {
        rechercheMapper = new RechercheMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rechercheMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rechercheMapper.fromId(null)).isNull();
    }
}
