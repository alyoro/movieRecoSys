package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import java.util.List;

@Data
public class Movie extends Node {

    @Property(name = "title")
    private String title;

    @Property(name = "director")
    private String director;

    @Property(name = "year")
    private String year;

    @Property(name = "type")
    private String type;

    @Relationship(type = "WATCHED", direction = Relationship.INCOMING)
    private List<User> watched;

}
