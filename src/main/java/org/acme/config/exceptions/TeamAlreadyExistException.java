package org.acme.config.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TeamAlreadyExistException extends WebApplicationException {

    public TeamAlreadyExistException(String id, String name) {
        super(
                String.format("A team with name %s, already exist, check id %s", name, id),
                Response.status(Response.Status.BAD_REQUEST)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(String.format("\"A team with name %s, already exist, check id %s\"", name, id))
                        .build()
        );
    }
}
