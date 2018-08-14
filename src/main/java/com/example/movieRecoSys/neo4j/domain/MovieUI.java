package com.example.movieRecoSys.neo4j.domain;

import lombok.Data;

@Data
public class MovieUI {
    Long id;
    String title;
    String director;
    String year;
    String type;
    Double avgScore;
}
