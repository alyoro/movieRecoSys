package com.example.movieRecoSys.neo4j.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

import javax.persistence.ColumnResult;

@QueryResult
public class WatchedDB {


    private long startNodeId;
    private long endNodeId;
    private int score;
}
