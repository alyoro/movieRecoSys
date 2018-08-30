package com.example.movieRecoSys.controller;

import lombok.Data;

@Data
public class RecommendationRequestBody {
    String type;
    String year;
    String director;
    int levelOfRecommendation;
}