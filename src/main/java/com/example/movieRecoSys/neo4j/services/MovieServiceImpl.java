package com.example.movieRecoSys.neo4j.services;

import com.example.movieRecoSys.neo4j.domain.MovieDB;
import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.repository.MovieRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    public List<MovieUI> getTopMovies(){

        var movies = movieRepository.getTopMovies();
        var moviesUI = new ArrayList<MovieUI>();
        MovieUI movieTmp = null;

        for(MovieDB movie : movies){
            movieTmp = new MovieUI();
            movieTmp.setId(movie.getMovie().getId());
            movieTmp.setTitle(movie.getMovie().getTitle());
            movieTmp.setDirector(movie.getMovie().getDirector());
            movieTmp.setYear(movie.getMovie().getYear());
            movieTmp.setType(movie.getMovie().getType());
            movieTmp.setAvgScore(movie.getAvgScore());

            moviesUI.add(movieTmp);
        }
        return  moviesUI;
    }
}
