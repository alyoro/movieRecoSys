package com.example.movieRecoSys.controller;

import lombok.Data;


/**
 * Class used to get data posted for evaluation movies
 */
@Data
public class EvaluateMovieRequestBody {
    long id;
    int score;
}
