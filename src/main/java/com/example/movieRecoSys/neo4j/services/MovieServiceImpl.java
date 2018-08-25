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

    @Override
    public List<MovieUI> getTopMovies(){
        return convertMoviesDBToUIList(movieRepository.getTopMovies());
    }

    @Override
    public List<MovieUI> getMovieByTitle(String title) {
       return convertMoviesDBToUIList(movieRepository.getMoviesByTitle(title));
    }

    @Override
    public MovieUI getMovieById(long id) {
        movieRepository.getMoviesById(id);
        return convertMoviesDBToUI(movieRepository.getMoviesById(id));
    }

//    --------------------------------------------------    //
    private MovieUI convertMoviesDBToUI(MovieDB movie){
        var movieTmp = new MovieUI();

        movieTmp.setId(movie.getMovie().getId());
        movieTmp.setTitle(movie.getMovie().getTitle());
        movieTmp.setDirector(movie.getMovie().getDirector());
        movieTmp.setYear(movie.getMovie().getYear());
        movieTmp.setType(movie.getMovie().getType());
        movieTmp.setAvgScore(movie.getAvgScore());

        return movieTmp;
    }

    private List<MovieUI> convertMoviesDBToUIList(List<MovieDB> movies){
        var moviesUI = new ArrayList<MovieUI>();

        for(MovieDB movie : movies){
            moviesUI.add(convertMoviesDBToUI(movie));
        }

        return moviesUI;
    }
}
