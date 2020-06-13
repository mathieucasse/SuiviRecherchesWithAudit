package ch.matfly.suivirecherches.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ch.matfly.suivirecherches.domain.Personne} entity.
 */
public class PersonneDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;


    private Long entrepriseId;

    private String entrepriseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public String getEntrepriseName() {
        return entrepriseName;
    }

    public void setEntrepriseName(String entrepriseName) {
        this.entrepriseName = entrepriseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonneDTO personneDTO = (PersonneDTO) o;
        if (personneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonneDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", entrepriseId=" + getEntrepriseId() +
            ", entrepriseName='" + getEntrepriseName() + "'" +
            "}";
    }
}
