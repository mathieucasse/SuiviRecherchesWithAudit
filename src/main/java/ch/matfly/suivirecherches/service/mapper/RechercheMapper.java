package ch.matfly.suivirecherches.service.mapper;


import ch.matfly.suivirecherches.domain.Recherche;
import ch.matfly.suivirecherches.service.dto.RechercheDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Recherche} and its DTO {@link RechercheDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, EntrepriseMapper.class, PersonneMapper.class})
public interface RechercheMapper extends EntityMapper<RechercheDTO, Recherche> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "entPrestataire.id", target = "entPrestataireId")
    @Mapping(source = "entPrestataire.name", target = "entPrestataireName")
    @Mapping(source = "entFinale.id", target = "entFinaleId")
    @Mapping(source = "entFinale.name", target = "entFinaleName")
    @Mapping(source = "contact.id", target = "contactId")
    @Mapping(source = "contact.lastName", target = "contactLastName")
    RechercheDTO toDto(Recherche recherche);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "entPrestataireId", target = "entPrestataire")
    @Mapping(source = "entFinaleId", target = "entFinale")
    @Mapping(source = "contactId", target = "contact")
    Recherche toEntity(RechercheDTO rechercheDTO);

    default Recherche fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recherche recherche = new Recherche();
        recherche.setId(id);
        return recherche;
    }
}
