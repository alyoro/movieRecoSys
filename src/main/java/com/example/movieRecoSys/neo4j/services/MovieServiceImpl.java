package com.example.movieRecoSys.neo4j.services;

import com.example.movieRecoSys.credential.service.SecurityContextUsername;
import com.example.movieRecoSys.neo4j.domain.*;
import com.example.movieRecoSys.neo4j.repository.MovieRepository;
import com.example.movieRecoSys.neo4j.repository.UserRepository;
import com.example.movieRecoSys.neo4j.repository.WatchedRepository;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@Transactional
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    WatchedRepository watchedRepository;

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
        if(!movieRepository.getMoviesByTitle(title).isEmpty()){
            return convertMoviesDBToUIList(movieRepository.getMoviesByTitle(title));
        }else if(movieRepository.getMovieByTitle(title)!=null) {
            var list = new ArrayList<MovieDB>();
            list.add(new MovieDB(movieRepository.getMovieByTitle(title),0.0));
            return convertMoviesDBToUIList(list);
        }else {
            return null;
        }
    }

    @Override
    public MovieUI getMovieById(long id) {
        log.info("getMovieById: "+id);
        movieRepository.getMoviesById(id);
        return convertMovieDBToUI(movieRepository.getMoviesById(id));
    }

    @Override
    public int evaluateMovie(long movieId, int score) {
        long userId = this.getUserID();

        User user = userRepository.findByUsername(securityContextUsername.getUsernameFromSecurityContext());
        Movie movie = movieRepository.getMovieById(movieId);
        log.info(watchedRepository.findByStartNodeAndEndNode(user,movie));
        if(watchedRepository.findByStartNodeAndEndNode(user,movie)==null) {
            log.info("evaluateMovie movieId: " + movieId + ", score: " + score);
            movieRepository.evaluateMovie(userId, movieId, score);
        }
        else {
            log.info("changeEvaluation movieId: "+ movieId + ", score: "+score);
            movieRepository.changeEvaluation(userId, movieId, score);
        }
        return 1;
    }

    @Override
    public int addNewMovie(Movie movie){
        log.info("addNewMovie:"+movie);
        movieRepository.save(movie);
        movieRepository.addRelationshipAdded(this.getUserID(),movieRepository.getMovieByTitle(movie.getTitle()).getId());
        return 1;
    }

    @Override
    public List<MovieUI> getRandomMovies(){
        log.info("getRandomMovies");
        return convertMoviesDBToUIList(movieRepository.getRandomMovies());
    }

    @Override
    public  List<MovieUI> getWatchedMovies(){
        return convertMoviesDBToUIList(movieRepository.getWatchedMovies(this.getUserID()));
    }

    @Override
    public List<MovieUI> getRecommendations(String reco, String data){
        if(reco.equals("director")){
            log.info("getRecommendations: "+reco);
            return convertMoviesDBToUIList(movieRepository.getRecommendationByDirector(this.getUserID(),data));
        }
        if(reco.equals("year")){
            log.info("getRecommendations: "+reco);
            return convertMoviesDBToUIList(movieRepository.getRecommendationByYear(this.getUserID(),data));
        }
        if(reco.equals("type")){
            log.info("getRecommendations: "+reco);
            return convertMoviesDBToUIList(movieRepository.getRecommendationByType(this.getUserID(),data));
        }
        else return null;
    }


//    --------------------------------------------------    //
    private MovieUI convertMovieDBToUI(MovieDB movie){
        var movieTmp = new MovieUI();

        movieTmp.setId(movie.getMovie().getId());
        movieTmp.setTitle(movie.getMovie().getTitle());
        movieTmp.setDirector(movie.getMovie().getDirector());
        movieTmp.setYear(movie.getMovie().getYear());
        movieTmp.setType(movie.getMovie().getType());
        movieTmp.setAvgScore(movie.getAvgScore());
        if(this.getUserID() == -1L) {
            movieTmp.setYourScore(0.0);
        }else {
            movieTmp.setYourScore(movieRepository.getYourEvaulationOfMovie(this.getUserID(), movie.getMovie().getId()));
        }
        return movieTmp;
    }

    private List<MovieUI> convertMoviesDBToUIList(List<MovieDB> movies){
        var moviesUI = new ArrayList<MovieUI>();

        for(MovieDB movie : movies){
            moviesUI.add(convertMovieDBToUI(movie));
        }

        return moviesUI;
    }

    private long getUserID(){
        if(securityContextUsername.getUsernameFromSecurityContext()=="anonymousUser"){
            return -1L;
        }else
        return userRepository.findByUsername(securityContextUsername.getUsernameFromSecurityContext()).getId();
    }
}
