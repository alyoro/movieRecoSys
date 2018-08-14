package com.example.movieRecoSys.controller;


import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.services.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "movies")
@Log4j2
public class MoviesController {

    @Autowired
    MovieService movieService;

    @RequestMapping(path = "/top")
    public List<MovieUI> getTopMovies(){
        log.info("Getting top Movies");
        return movieService.getTopMovies();
    }


    
}
