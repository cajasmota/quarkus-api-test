package org.acme.models;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDate;

public class Player extends PanacheMongoEntity {

    public String name;
    public LocalDate birthdate;
    public String position;
    public int number;
    public String photoURL;
}