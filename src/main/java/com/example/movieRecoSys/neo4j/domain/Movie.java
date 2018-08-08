package com.example.movieRecoSys.neo4j.domain;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getWatched() {
        return watched;
    }

    public void setWatched(List<User> watched) {
        this.watched = watched;
    }

}
