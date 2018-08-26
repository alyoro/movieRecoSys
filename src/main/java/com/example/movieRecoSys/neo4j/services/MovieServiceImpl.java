package com.example.movieRecoSys.neo4j.services;

import com.example.movieRecoSys.credential.service.SecurityContextUsername;
import com.example.movieRecoSys.credential.service.UserDetailsServiceImpl;
import com.example.movieRecoSys.neo4j.domain.MovieDB;
import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.repository.MovieRepository;
import com.example.movieRecoSys.neo4j.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityContextUsername securityContextUsername;

    @Override
    public List<MovieUI> getTopMovies(){
        log.info("getTopMovies");
        return convertMoviesDBToUIList(movieRepository.getTopMovies());
    }

    @Override
    public List<MovieUI> getMovieByTitle(String title) {
        log.info("getMovieByTitle: "+title);
       return convertMoviesDBToUIList(movieRepository.getMoviesByTitle(title));
    }

    @Override
    public MovieUI getMovieById(long id) {
        log.info("getMovieById: "+id);
        movieRepository.getMoviesById(id);
        return convertMoviesDBToUI(movieRepository.getMoviesById(id));
    }

    @Override
    public void evaluateMovie(long id, int score){
        log.info("evaluateMovie id: "+id+", score: "+score);
        long userId = userRepository.findByUsername(securityContextUsername.getUsernameFromSecurityContext()).getId();
        movieRepository.evaluateMovie(id,score,userId);
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
