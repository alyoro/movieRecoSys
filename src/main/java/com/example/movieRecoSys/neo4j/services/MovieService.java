package com.example.movieRecoSys.neo4j.services;

        import com.example.movieRecoSys.neo4j.domain.Movie;
        import com.example.movieRecoSys.neo4j.domain.MovieUI;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public interface MovieService {

        List<MovieUI> getTopMovies();

        List<MovieUI> getMovieByTitle(String title);

        List<MovieUI> getRandomMovies();

        List<MovieUI> getWatchedMovies();

        List<MovieUI> getRecommendations(String reco, String data);

        MovieUI getMovieById(long id);

        int evaluateMovie(long id, int score);

        int addNewMovie(Movie movie);


}
