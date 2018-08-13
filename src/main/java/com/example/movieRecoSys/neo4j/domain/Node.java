package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@NodeEntity
@Data
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "")
    private Edge edge;
}
