package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
public class Edge {

    @Id
    private Long id;

    @StartNode
    private Node startNode;

    @EndNode
    private Node endNode;

}
