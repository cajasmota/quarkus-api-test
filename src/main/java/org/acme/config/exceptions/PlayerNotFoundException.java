package org.acme.config.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PlayerNotFoundException extends WebApplicationException {

    public PlayerNotFoundException(String id) {
        super(
                String.format("A player with id %s does not exist", id),
                Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(String.format("\"A player with id %s does not exist\"", id))
                        .build()
        );
    }
}
