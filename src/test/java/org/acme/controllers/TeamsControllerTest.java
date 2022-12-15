package org.acme.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TeamsControllerTest {

    @Test
    void test_EmptyResponse(){
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/")
                .then()
                .statusCode(200);
    }

}