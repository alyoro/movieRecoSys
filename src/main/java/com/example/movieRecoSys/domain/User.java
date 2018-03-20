package com.example.movieRecoSys.domain;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

public class User extends Node {

    @Property(name = "username")
    private String username;

    @Relationship(type = "WATCHED", direction = Relationship.OUTGOING)
    private List<Movie> watched;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Movie> getWatched() {
        return watched;
    }

    public void setWatched(List<Movie> watched) {
        this.watched = watched;
    }

}
