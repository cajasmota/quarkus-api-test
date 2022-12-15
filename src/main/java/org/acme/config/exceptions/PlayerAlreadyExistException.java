package org.acme.config.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

public class PlayerAlreadyExistException extends WebApplicationException {

    public PlayerAlreadyExistException(String id, String name, LocalDate birthDate, String countryOfOrigin) {
        super(
                String.format("A player with name %s, birhtDate %s and Country of origin %s exist, check id %s", name, birthDate, countryOfOrigin, id),
                Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(String.format("\"A player with name %s, birhtDate %s and Country of origin %s exist, check id %s\"", name, birthDate, countryOfOrigin, id))
                        .build()
        );
    }
}
