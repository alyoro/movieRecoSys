package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
@Data
public class MovieDB {
    Movie movie;
    Double avgScore;
}