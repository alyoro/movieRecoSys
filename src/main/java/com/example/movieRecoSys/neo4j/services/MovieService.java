package com.example.movieRecoSys.neo4j.services;

        import com.example.movieRecoSys.neo4j.domain.Movie;
        import com.example.movieRecoSys.neo4j.domain.MovieUI;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public interface MovieService {

    /**
     * Returns top movies
     * @return list of moviesUI
     */
    List<MovieUI> getTopMovies();

    /**
     *  Returns movies by part or full title
     * @param title part/full title to find
     * @return list of moviesUI
     */
    List<MovieUI> getMovieByTitle(String title);

    /**
     * Returns random movies
     * @return list of moviesUI
     */
    List<MovieUI> getRandomMovies();

    /**
     * Returns movies watched by specific user
     * @return list of moviesUI
     */
    List<MovieUI> getWatchedMovies();

    /**
     * Recommendations by specific property and passed value
     * @param reco name of property
     * @param data value of property
     * @return list of moviesUI
     */
    List<MovieUI> getRecommendations(String reco, String data);

    /**
     * Returns movie by passed id
     * @param id of movie to get
     * @return movie of specific id
     */
    MovieUI getMovieById(long id);

    /**
     * Evaluate a movie with specific id with score
     * @param id id of movie to Evaluate
     * @param score score to evaluate
     * @return success or not
     */
    int evaluateMovie(long id, int score);

    /**
     * Added new movie
     * @param movie movie to add
     * @return success or not
     */
    int addNewMovie(Movie movie);


}
