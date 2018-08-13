package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import java.util.List;

@Data
public class User extends Node {

    @Property(name = "username")
    private String username;

    @Relationship(type = "WATCHED", direction = Relationship.OUTGOING)
    private List<Movie> watched;
}
