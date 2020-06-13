package ch.matfly.suivirecherches.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ch.matfly.suivirecherches.web.rest.TestUtil;

public class RechercheTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recherche.class);
        Recherche recherche1 = new Recherche();
        recherche1.setId(1L);
        Recherche recherche2 = new Recherche();
        recherche2.setId(recherche1.getId());
        assertThat(recherche1).isEqualTo(recherche2);
        recherche2.setId(2L);
        assertThat(recherche1).isNotEqualTo(recherche2);
        recherche1.setId(null);
        assertThat(recherche1).isNotEqualTo(recherche2);
    }
}
