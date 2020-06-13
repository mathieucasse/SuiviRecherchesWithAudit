package ch.matfly.suivirecherches.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonneMapperTest {

    private PersonneMapper personneMapper;

    @BeforeEach
    public void setUp() {
        personneMapper = new PersonneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personneMapper.fromId(null)).isNull();
    }
}
