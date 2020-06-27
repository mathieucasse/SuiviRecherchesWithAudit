package ch.matfly.suivirecherches.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ch.matfly.suivirecherches.domain.Entreprise} entity.
 */
public class EntrepriseDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private String name;

    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntrepriseDTO entrepriseDTO = (EntrepriseDTO) o;
        if (entrepriseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entrepriseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntrepriseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
