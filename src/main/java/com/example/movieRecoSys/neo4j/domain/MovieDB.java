package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

import javax.ws.rs.DefaultValue;

@QueryResult
@Data
public class MovieDB {
    Movie movie;
    double avgScore;

    public MovieDB(){}

    public MovieDB(Movie movie, double avgScore){
        this.movie =  movie;
        this.avgScore = avgScore;
    }
}