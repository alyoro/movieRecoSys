package com.example.movieRecoSys.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieUI {
    Long id;
    String title;
    String director;
    String year;
    String type;
    Double avgScore;
    Double yourScore;
}
