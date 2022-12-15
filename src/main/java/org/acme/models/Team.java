package org.acme.models;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;
import java.util.List;

@MongoEntity(collection = "Teams")
public class Team extends PanacheMongoEntity {

    public String name;
    public String flagUrl;
    public List<Player> players = new ArrayList<>();

}
