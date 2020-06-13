package ch.matfly.suivirecherches.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ch.matfly.suivirecherches.web.rest.TestUtil;

public class RechercheDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RechercheDTO.class);
        RechercheDTO rechercheDTO1 = new RechercheDTO();
        rechercheDTO1.setId(1L);
        RechercheDTO rechercheDTO2 = new RechercheDTO();
        assertThat(rechercheDTO1).isNotEqualTo(rechercheDTO2);
        rechercheDTO2.setId(rechercheDTO1.getId());
        assertThat(rechercheDTO1).isEqualTo(rechercheDTO2);
        rechercheDTO2.setId(2L);
        assertThat(rechercheDTO1).isNotEqualTo(rechercheDTO2);
        rechercheDTO1.setId(null);
        assertThat(rechercheDTO1).isNotEqualTo(rechercheDTO2);
    }
}
