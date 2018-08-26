package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;


import java.util.List;

@Data
public class User extends Node {

    @Property(name = "username")
    @Index(unique = true)
    private String username;

    @Relationship(type = "WATCHED", direction = Relationship.OUTGOING)
    private List<Movie> watched;

    public User(){}

    public User(String username) {
        this.username = username;
    }
}
