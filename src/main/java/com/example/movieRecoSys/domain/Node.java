package com.example.movieRecoSys.domain;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "")
    private Edge edge;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
