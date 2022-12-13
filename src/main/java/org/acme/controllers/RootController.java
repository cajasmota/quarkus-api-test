package org.acme.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RootController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Players API";
    }
}