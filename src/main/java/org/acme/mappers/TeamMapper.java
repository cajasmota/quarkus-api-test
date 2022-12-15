package org.acme.mappers;

import org.acme.controllers.request.CreatePlayerRequest;
import org.acme.controllers.request.CreateTeamRequest;
import org.acme.controllers.request.UpdatePlayerRequest;
import org.acme.controllers.request.UpdateTeamRequest;
import org.acme.dto.PlayerDTO;
import org.acme.dto.TeamDTO;
import org.acme.models.Player;
import org.acme.models.Team;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TeamMapper {

    Team mapFromCreateRequest(CreateTeamRequest source);

    @Mapping(target = "_id", source = "id")
    TeamDTO mapFromEntity(Team source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromUpdateRequest(UpdateTeamRequest source, @MappingTarget Team target);

    @Mapping(target = "_id", source = "id")
    PlayerDTO mapFromEntity(Player source);
    List<PlayerDTO> mapFromEntityList(List<Player> source);

    Player mapFromCreateRequest(CreatePlayerRequest source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromUpdatePlayerRequest(UpdatePlayerRequest source, @MappingTarget Player target);

}
