package ch.matfly.suivirecherches.service.mapper;


import ch.matfly.suivirecherches.domain.*;
import ch.matfly.suivirecherches.service.dto.PersonneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Personne} and its DTO {@link PersonneDTO}.
 */
@Mapper(componentModel = "spring", uses = {EntrepriseMapper.class})
public interface PersonneMapper extends EntityMapper<PersonneDTO, Personne> {

    @Mapping(source = "entreprise.id", target = "entrepriseId")
    @Mapping(source = "entreprise.name", target = "entrepriseName")
    PersonneDTO toDto(Personne personne);

    @Mapping(source = "entrepriseId", target = "entreprise")
    Personne toEntity(PersonneDTO personneDTO);

    default Personne fromId(Long id) {
        if (id == null) {
            return null;
        }
        Personne personne = new Personne();
        personne.setId(id);
        return personne;
    }
}
