package com.example.movieRecoSys.domain;

import org.neo4j.ogm.annotation.*;


public class Edge {

    @Id
    private Long id;

    @StartNode
    private Node startNode;

    @EndNode
    private Node endNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }
}
