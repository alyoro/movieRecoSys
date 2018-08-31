package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieDB;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    /**
     * Return movieDB(with avgscore) with specific id
     * @param id id
     * @return List of moviesDB
     */
    @Query("match(:User)-[n:WATCHED]->(m:Movie) where ID(m)={0} return m as movie, avg(n.score) as avgScore")
    MovieDB getMoviesById(Long id);

    /**
     * Return movie with specific id
     * @param id id
     * @return
     */
    @Query("match(m:Movie) where ID(m)={0} return m as movie")
    Movie getMovieById(Long id);

    /**
     * Return list of movies containing data in title
     * @param title part/full title of movie
     * @return List of moviesDB
     */
    @Query("match(:User)-[n:WATCHED|:ADDED]->(m:Movie) where m.title contains {0} return m as movie, avg(n.score) as avgScore")
    List<MovieDB> getMoviesByTitle(String title);

    /**
     * Return movie with exact movie title
     * @param title movie title
     * @return one movie
     */
    @Query("match (m:Movie) where m.title={0} return m as movie")
    Movie getMovieByTitle(String title);

    /**
     * Returns top movies ordered descending
     * @return List of top movies
     */
    @Query("match(:User)-[n:WATCHED]->(m:Movie) return m as movie, avg(n.score) as avgScore order by avgScore desc")
    List<MovieDB> getTopMovies();

    /**
     * Making new relationship with score added by user
     * @param userId user id in graphDB
     * @param movieId movie id in graphDB
     * @param userScore user score
     */
    @Query("match(m:Movie),(u:User) where ID(u)={0} and ID(m)={1} create (u)-[:WATCHED {score:  {2} }]->(m)")
    void evaluateMovie(long userId, long movieId, int userScore);

    /**
     * Changing evaluation to prevent making more than 2 evaluation between specific user and specific movie
     * @param userId user id in graphDB
     * @param movieId movie id in graphDB
     * @param userScore new score
     */
    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} set w.score = {2}")
    void changeEvaluation(long userId, long movieId, int userScore);

    /**
     * Return random movies
     * @return List of random moviesDB
     */
    @Query("match (:User)-[n:ADDED|:WATCHED]->(m:Movie) return m as movie, avg(n.score) as avgScore order by rand() limit 10")
    List<MovieDB> getRandomMovies();

    /**
     * Adding relationship that describes which user add movie to db
     * @param userId  user id in graphDB
     * @param movieId movie id in graphDB
     */
    @Query("match(u:User),(m:Movie) where ID(u)={0} and ID(m)={1} create (u)-[:ADDED]->(m)")
    void addRelationshipAdded(long userId, long movieId);

    /**
     * Returns evaluation of user and movie
     * @param userId user id in graphDB
     * @param movieId movie id in graphDB
     * @return your score
     */
    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} return w.score")
    Double getYourEvaulationOfMovie(long userId, long movieId);

    /**
     * Recommendation By Director
     * @param userId userid
     * @param reco value of property to find
     * @return List of moviesDB
     */
    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie )<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and m2.director = {1} and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByDirector(long userId, String reco);

    /**
     * Recommendation By Year
     * @param userId userid
     * @param reco value of property to find
     * @return List of moviesDB
     */
    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie )<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and m2.year = {1} and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByYear(long userId, String reco);

    /**
     * Recommendation By Type
     * @param userId userid
     * @param reco value of property to find
     * @return List of moviesDB
     */
    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie )<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and m2.type  = {1} and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByType(long userId, String reco);

    /**
     * Returns watched movies by user with userId
     * @param userId user id in graphDB
     * @return List of watched moviesDB
     */
    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} return m as movie, avg(w.score) as avgScore")
    List<MovieDB> getWatchedMovies(long userId);
}