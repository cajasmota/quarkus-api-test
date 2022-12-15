package org.acme.config.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvalidIdException extends WebApplicationException {

    public InvalidIdException(String id) {
        super(
                String.format("The provided id %s is invalid", id),
                Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(String.format("\"The provided id %s is invalid\"", id))
                        .build()
        );
    }

}
