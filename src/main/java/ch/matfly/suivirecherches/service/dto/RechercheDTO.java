package ch.matfly.suivirecherches.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import ch.matfly.suivirecherches.domain.enumeration.OffreDeService;
import ch.matfly.suivirecherches.domain.enumeration.ResOffreDeService;

/**
 * A DTO for the {@link ch.matfly.suivirecherches.domain.Recherche} entity.
 */
public class RechercheDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private LocalDate date;

    private String poste;

    @Lob
    private String desciptif;

    private String location;

    private Boolean assignationORP;

    @Min(value = 1)
    @Max(value = 100)
    private Integer txactivite;

    private OffreDeService offredeservice;

    private ResOffreDeService resoffredeservice;

    private String motifres;


    private Long entPrestataireId;

    private String entPrestataireName;

    private Long entFinaleId;

    private String entFinaleName;

    private Long contactId;

    private String contactLastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDesciptif() {
        return desciptif;
    }

    public void setDesciptif(String desciptif) {
        this.desciptif = desciptif;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean isAssignationORP() {
        return assignationORP;
    }

    public void setAssignationORP(Boolean assignationORP) {
        this.assignationORP = assignationORP;
    }

    public Integer getTxactivite() {
        return txactivite;
    }

    public void setTxactivite(Integer txactivite) {
        this.txactivite = txactivite;
    }

    public OffreDeService getOffredeservice() {
        return offredeservice;
    }

    public void setOffredeservice(OffreDeService offredeservice) {
        this.offredeservice = offredeservice;
    }

    public ResOffreDeService getResoffredeservice() {
        return resoffredeservice;
    }

    public void setResoffredeservice(ResOffreDeService resoffredeservice) {
        this.resoffredeservice = resoffredeservice;
    }

    public String getMotifres() {
        return motifres;
    }

    public void setMotifres(String motifres) {
        this.motifres = motifres;
    }

    public Long getEntPrestataireId() {
        return entPrestataireId;
    }

    public void setEntPrestataireId(Long entrepriseId) {
        this.entPrestataireId = entrepriseId;
    }

    public String getEntPrestataireName() {
        return entPrestataireName;
    }

    public void setEntPrestataireName(String entrepriseName) {
        this.entPrestataireName = entrepriseName;
    }

    public Long getEntFinaleId() {
        return entFinaleId;
    }

    public void setEntFinaleId(Long entrepriseId) {
        this.entFinaleId = entrepriseId;
    }

    public String getEntFinaleName() {
        return entFinaleName;
    }

    public void setEntFinaleName(String entrepriseName) {
        this.entFinaleName = entrepriseName;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long personneId) {
        this.contactId = personneId;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String personneLastName) {
        this.contactLastName = personneLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RechercheDTO rechercheDTO = (RechercheDTO) o;
        if (rechercheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rechercheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RechercheDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", poste='" + getPoste() + "'" +
            ", desciptif='" + getDesciptif() + "'" +
            ", location='" + getLocation() + "'" +
            ", assignationORP='" + isAssignationORP() + "'" +
            ", txactivite=" + getTxactivite() +
            ", offredeservice='" + getOffredeservice() + "'" +
            ", resoffredeservice='" + getResoffredeservice() + "'" +
            ", motifres='" + getMotifres() + "'" +
            ", entPrestataireId=" + getEntPrestataireId() +
            ", entPrestataireName='" + getEntPrestataireName() + "'" +
            ", entFinaleId=" + getEntFinaleId() +
            ", entFinaleName='" + getEntFinaleName() + "'" +
            ", contactId=" + getContactId() +
            ", contactLastName='" + getContactLastName() + "'" +
            "}";
    }
}
