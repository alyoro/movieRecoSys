package com.example.movieRecoSys.controller;

import com.example.movieRecoSys.aspect.LogExecutionTime;
import com.example.movieRecoSys.credential.service.SecurityContextUsername;
import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.services.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * Unsecured controller for not logged and logged users
 */
@RestController
@RequestMapping(path = "movies")
@Log4j2
public class MoviesController {

    @Autowired
    MovieService movieService;

    /**
     * Returns top movies
     * @return List of sorted movies by average score
     */
    @LogExecutionTime
    @RequestMapping(path = "/top")
    public List<MovieUI> getTopMovies(){
        log.info("Getting top Movies");
        return movieService.getTopMovies();
    }

    /**
     * Return movies by Title
     * @param title part/full name of searching title
     * @return List of movies that contans title
     */
    @LogExecutionTime
    @RequestMapping(path = "/search/title")
    public List<MovieUI> searchMovieByTitle(String title){
        log.info("Searching for movie by Title: "+title);
        return movieService.getMovieByTitle(title);
    }

    /**
     * Rturn random movies
     * @return List of random fetched movies
     */
    @LogExecutionTime
    @RequestMapping(path = "/random")
    public List<MovieUI> getRandomMovies(){
        log.info("Getting random Movies");
        return movieService.getRandomMovies();
    }

}
