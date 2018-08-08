package com.example.movieRecoSys.neo4j.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieUI {
    Movie movie;
    Double avgScore;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }
}