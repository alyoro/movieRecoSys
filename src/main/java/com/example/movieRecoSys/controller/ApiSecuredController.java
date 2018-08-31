package com.example.movieRecoSys.controller;

import com.example.movieRecoSys.aspect.LogExecutionTime;
import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.services.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Secured controller for logged users
 */
@RestController
@RequestMapping(path = "api")
@Log4j2
public class ApiSecuredController {

    @Autowired
    MovieService movieService;

    /**
     * Adding new movies to database
     * @param movie json contains (title, director, year, type)
     * @return status of action
     */
    @LogExecutionTime
    @RequestMapping(path = "/movies/add")
    public ResponseEntity addNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
        return ResponseEntity.ok(null);
    }


    /**
     * Evaluation of movie
     * @param evaluateMovieRequestBody json to evaluate contains(id,score)
     * @return status of action
     */
    @LogExecutionTime
    @RequestMapping(path = "/movies/evaluate")
    public ResponseEntity evaluateMovie(@RequestBody EvaluateMovieRequestBody evaluateMovieRequestBody){
        if(movieService.evaluateMovie(evaluateMovieRequestBody.getId(), evaluateMovieRequestBody.getScore())==1){
            return ResponseEntity.ok(null);
        }else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    /**
     * Returning watched movies by user used link
     * @return List of watched movies
     */
    @LogExecutionTime
    @RequestMapping(path = "/movies/watched")
    public List<MovieUI> getWatchedMovies(){
        return movieService.getWatchedMovies();
    }

    /**
     *
     * @param reco by which property gets recommendation
     * @param data value of looking property
     * @return List of recommended movies
     */
    @LogExecutionTime
    @RequestMapping(path = "/movies/reco")
    public List<MovieUI> getRecommendations(@RequestParam String reco, @RequestParam String data){
        return movieService.getRecommendations(reco, data);
    }
}
