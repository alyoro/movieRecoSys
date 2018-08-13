package com.example.movieRecoSys.controller;


import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "movies")
public class MoviesController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(path = "/top")
    public List<MovieUI> getTopMovies(){
        return movieRepository.getTopMovies();
    }
}
