package org.acme.repositories;

import org.acme.config.exceptions.InvalidIdException;
import org.acme.config.exceptions.PlayerNotFoundException;
import org.acme.config.exceptions.TeamAlreadyExistException;
import org.acme.config.exceptions.TeamNotFoundException;
import org.acme.controllers.request.CreatePlayerRequest;
import org.acme.controllers.request.CreateTeamRequest;
import org.acme.controllers.request.UpdatePlayerRequest;
import org.acme.controllers.request.UpdateTeamRequest;
import org.acme.dto.PlayerDTO;
import org.acme.dto.TeamDTO;
import org.acme.dto.TeamDetailsDTO;
import org.acme.mappers.TeamMapper;
import org.acme.models.Player;
import org.acme.models.Team;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TeamRepository {

    @Inject
    TeamMapper mapper;

    private ObjectId idFromString(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException ex) {
            throw new InvalidIdException(id);
        }
    }

    public TeamDetailsDTO findByName(String name) {
        TeamDetailsDTO team = Team.find("name", name)
                .project(TeamDetailsDTO.class)
                .firstResult();

        if (team == null) {
            throw new TeamNotFoundException(name);
        }

        return team;
    }

    public TeamDetailsDTO findById(String id) {
        TeamDetailsDTO team = Team.find("_id", idFromString(id))
                .project(TeamDetailsDTO.class)
                .firstResult();

        if (team == null) {
            throw new TeamNotFoundException(id);
        }

        return team;
    }

    public List<TeamDTO> findAll() {
        return Team
                .findAll()
                .project(TeamDTO.class)
                .list();
    }

    public TeamDTO create(CreateTeamRequest request) {

        Team team = Team.find("name", request.name())
                .firstResult();

        if (team != null) {
            throw new TeamAlreadyExistException(team.id.toString(), team.name);
        }

        team = mapper.mapFromCreateRequest(request);
        team.persist();

        return mapper.mapFromEntity(team);
    }

    public TeamDTO update(String id, UpdateTeamRequest request) {
        Team team = Team.findById(idFromString(id));
        if (team == null) {
            throw new TeamNotFoundException(id);
        }

        mapper.mapFromUpdateRequest(request, team);
        team.update();

        return mapper.mapFromEntity(team);
    }

    public TeamDTO delete(String id) {
        Team team = Team.findById(idFromString(id));
        if (team == null) {
            throw new TeamNotFoundException(id);
        }

        team.delete();

        return mapper.mapFromEntity(team);
    }

    public List<PlayerDTO> allPlayers(String teamId) {
        Team team = Team.findById(idFromString(teamId));
        if (team == null) {
            throw new TeamNotFoundException(teamId);
        }

        return mapper.mapFromEntityList(team.players);

    }

    public PlayerDTO createPlayer(String teamId, CreatePlayerRequest request) {
        Team team = Team.findById(idFromString(teamId));
        if (team == null) {
            throw new TeamNotFoundException(teamId);
        }

        Player player = mapper.mapFromCreateRequest(request);
        player.id = new ObjectId();

        team.players.add(player);
        team.update();

        return mapper.mapFromEntity(player);
    }

    public PlayerDTO playerById(String teamId, String playerId) {

        Player player = getPlayer(teamId, playerId);
        return mapper.mapFromEntity(player);
    }


    public PlayerDTO updatePlayer(String teamId, String playerId, UpdatePlayerRequest request) {

        Team team = Team.findById(idFromString(teamId));
        if (team == null) {
            throw new TeamNotFoundException(teamId);
        }

        ObjectId oid = idFromString(playerId);
        Player player = null;
        for( Player p: team.players){
            if(oid.equals(p.id)){
                player = p;
                mapper.mapFromUpdatePlayerRequest(request, p);
                break;
            }
        }

        if (player == null) {
            throw new PlayerNotFoundException(playerId);
        }

        team.update();

        return mapper.mapFromEntity(player);
    }

    public PlayerDTO delete(String teamId, String playerId) {
        Team team = Team.findById(idFromString(teamId));
        if (team == null) {
            throw new TeamNotFoundException(teamId);
        }

        ObjectId oid = idFromString(playerId);
        Player player = null;
        for (Player p : team.players) {
            if (oid.equals(p.id)) {
                player = p;
                team.players.remove(p);
                break;
            }
        }

        if (player == null) {
            throw new PlayerNotFoundException(playerId);
        }
        team.update();

        return mapper.mapFromEntity(player);
    }

    private Player getPlayer(String teamId, String playerId) {
        Team team = Team.findById(idFromString(teamId));
        if (team == null) {
            throw new TeamNotFoundException(teamId);
        }

        Player player = team.players
                .stream()
                .filter(p -> idFromString(playerId).equals(p.id))
                .findAny()
                .orElse(null);

        if (player == null) {
            throw new PlayerNotFoundException(playerId);
        }
        return player;
    }
}
