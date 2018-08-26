package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "WATCHED")
@Data
public class Watched extends Edge {

    @Property(name = "score")
    private int score;

    Watched(){}

    public Watched(Node startNode, Node endNode, int score){
        this.setStartNode(startNode);
        this.setEndNode(endNode);
        this.score = score;
    }
}
