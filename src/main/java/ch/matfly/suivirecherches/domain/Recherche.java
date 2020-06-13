package ch.matfly.suivirecherches.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import ch.matfly.suivirecherches.domain.enumeration.OffreDeService;

import ch.matfly.suivirecherches.domain.enumeration.ResOffreDeService;

/**
 * A Recherche.
 */
@Entity
@Table(name = "recherche")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Recherche extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Column(name = "poste")
    private String poste;

    @Column(name = "location")
    private String location;

    @Column(name = "assignation_orp")
    private Boolean assignationORP;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "txactivite")
    private Integer txactivite;

    @Enumerated(EnumType.STRING)
    @Column(name = "offredeservice")
    private OffreDeService offredeservice;

    @Enumerated(EnumType.STRING)
    @Column(name = "resoffredeservice")
    private ResOffreDeService resoffredeservice;

    @Column(name = "motifres")
    private String motifres;

    @ManyToOne
    @JsonIgnoreProperties("recherches")
    private Entreprise entPrestataire;

    @ManyToOne
    @JsonIgnoreProperties("recherches")
    private Entreprise entFinale;

    @ManyToOne
    @JsonIgnoreProperties("recherches")
    private Personne contact;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public Recherche date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getPoste() {
        return poste;
    }

    public Recherche poste(String poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getLocation() {
        return location;
    }

    public Recherche location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean isAssignationORP() {
        return assignationORP;
    }

    public Recherche assignationORP(Boolean assignationORP) {
        this.assignationORP = assignationORP;
        return this;
    }

    public void setAssignationORP(Boolean assignationORP) {
        this.assignationORP = assignationORP;
    }

    public Integer getTxactivite() {
        return txactivite;
    }

    public Recherche txactivite(Integer txactivite) {
        this.txactivite = txactivite;
        return this;
    }

    public void setTxactivite(Integer txactivite) {
        this.txactivite = txactivite;
    }

    public OffreDeService getOffredeservice() {
        return offredeservice;
    }

    public Recherche offredeservice(OffreDeService offredeservice) {
        this.offredeservice = offredeservice;
        return this;
    }

    public void setOffredeservice(OffreDeService offredeservice) {
        this.offredeservice = offredeservice;
    }

    public ResOffreDeService getResoffredeservice() {
        return resoffredeservice;
    }

    public Recherche resoffredeservice(ResOffreDeService resoffredeservice) {
        this.resoffredeservice = resoffredeservice;
        return this;
    }

    public void setResoffredeservice(ResOffreDeService resoffredeservice) {
        this.resoffredeservice = resoffredeservice;
    }

    public String getMotifres() {
        return motifres;
    }

    public Recherche motifres(String motifres) {
        this.motifres = motifres;
        return this;
    }

    public void setMotifres(String motifres) {
        this.motifres = motifres;
    }

    public Entreprise getEntPrestataire() {
        return entPrestataire;
    }

    public Recherche entPrestataire(Entreprise entreprise) {
        this.entPrestataire = entreprise;
        return this;
    }

    public void setEntPrestataire(Entreprise entreprise) {
        this.entPrestataire = entreprise;
    }

    public Entreprise getEntFinale() {
        return entFinale;
    }

    public Recherche entFinale(Entreprise entreprise) {
        this.entFinale = entreprise;
        return this;
    }

    public void setEntFinale(Entreprise entreprise) {
        this.entFinale = entreprise;
    }

    public Personne getContact() {
        return contact;
    }

    public Recherche contact(Personne personne) {
        this.contact = personne;
        return this;
    }

    public void setContact(Personne personne) {
        this.contact = personne;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recherche)) {
            return false;
        }
        return id != null && id.equals(((Recherche) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Recherche{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", poste='" + getPoste() + "'" +
            ", location='" + getLocation() + "'" +
            ", assignationORP='" + isAssignationORP() + "'" +
            ", txactivite=" + getTxactivite() +
            ", offredeservice='" + getOffredeservice() + "'" +
            ", resoffredeservice='" + getResoffredeservice() + "'" +
            ", motifres='" + getMotifres() + "'" +
            "}";
    }
}
