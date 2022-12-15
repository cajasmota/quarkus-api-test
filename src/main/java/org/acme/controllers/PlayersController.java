package org.acme.controllers;

import org.acme.controllers.request.CreatePlayerRequest;
import org.acme.controllers.request.UpdatePlayerRequest;
import org.acme.dto.PlayerDTO;
import org.acme.repositories.TeamRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/{teamId}/players")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayersController {

    @Inject
    TeamRepository teamRepository;

    @PathParam("teamId")
    String teamId;

    @GET
    @PermitAll
    public List<PlayerDTO> getAll() {
        return teamRepository.allPlayers(teamId);
    }

    @GET
    @Path("/{playerId}")
    @PermitAll
    public PlayerDTO byId(@PathParam("playerId") String playerId) {
        return teamRepository.playerById(teamId, playerId);
    }

    @POST
    @RolesAllowed({"admin"})
    public PlayerDTO create(@Valid @NotNull CreatePlayerRequest request) {
        return teamRepository.createPlayer(teamId, request);
    }

    @PUT
    @Path("/{playerId}")
    @RolesAllowed({"admin"})
    public PlayerDTO update(@PathParam("playerId") String playerId, @Valid @NotNull UpdatePlayerRequest request) {
        return teamRepository.updatePlayer(teamId, playerId, request);
    }

    @DELETE
    @Path("/{playerId}")
    @RolesAllowed({"admin"})
    public PlayerDTO delete(@PathParam("playerId") String playerId) {
        return teamRepository.delete(teamId, playerId);
    }

}
