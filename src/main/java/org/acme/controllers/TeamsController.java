package org.acme.controllers;

import org.acme.controllers.request.CreateTeamRequest;
import org.acme.controllers.request.UpdateTeamRequest;
import org.acme.dto.TeamDTO;
import org.acme.dto.TeamDetailsDTO;
import org.acme.repositories.TeamRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamsController {

    @Inject
    TeamRepository teamRepository;

    @GET
    @PermitAll
    public List<TeamDTO> all() {
        return teamRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public TeamDetailsDTO findById(@PathParam("id") String id) {
        return teamRepository.findById(id);
    }

    @GET
    @Path("/search")
    @PermitAll
    public TeamDetailsDTO findByName(@NotNull @QueryParam("name") String name) {
        return teamRepository.findByName(name);
    }

    @POST
    @RolesAllowed({"admin"})
    public TeamDTO create(@Valid @NotNull CreateTeamRequest request) {
        return teamRepository.create(request);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public TeamDTO update(@PathParam("id") String id, @Valid @NotNull UpdateTeamRequest request) {
        return teamRepository.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public TeamDTO delete(@PathParam("id") String id) {
        return teamRepository.delete(id);
    }
}

