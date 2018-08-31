package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieDB;
import com.example.movieRecoSys.neo4j.domain.Watched;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {


    @Query("match(:User)-[n:WATCHED]->(m:Movie) where ID(m)={0} return m as movie, avg(n.score) as avgScore")
    MovieDB getMoviesById(Long id);

    @Query("match(m:Movie) where ID(m)={0} return m as movie")
    Movie getMovieById(Long id);

    @Query("match(:User)-[n:WATCHED|:ADDED]->(m:Movie) where m.title contains {0} return m as movie, avg(n.score) as avgScore")
    List<MovieDB> getMoviesByTitle(String title);

    @Query("match (m:Movie) where m.title={0} return m as movie")
    Movie getMovieByTitle(String title);

    @Query("match(:User)-[n:WATCHED]->(m:Movie) return m as movie, avg(n.score) as avgScore order by avgScore desc")
    List<MovieDB> getTopMovies();

    @Query("match(m:Movie),(u:User) where ID(u)={0} and ID(m)={1} create (u)-[:WATCHED {score:  {2} }]->(m)")
    void evaluateMovie(long userId, long movieId, int userScore);

    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} set w.score = {2}")
    void changeEvaluation(long userId, long movieId, int userScore);

    @Query("match (u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} return w as watched")
    Watched checkIfThereIsRelationshipInDBAlready(long userId, long movieId);

    @Query("match (:User)-[n:ADDED|:WATCHED]->(m:Movie) return m as movie, avg(n.score) as avgScore order by rand() limit 10")
    List<MovieDB> getRandomMovies();

    @Query("match(u:User),(m:Movie) where ID(u)={0} and ID(m)={1} create (u)-[:ADDED]->(m)")
    void addRelationshipAdded(long userId, long movieId);

    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} return w.score")
    Double getYourEvaulationOfMovie(long userId, long movieId);

    @Query("match (u:User)-[w:WATCHED|:ADDED]->(m:Movie) with avg(w.score) as avgScore, m as movie where movie.year={0} and avgScore>={1} and avgScore<={2} return movie, avgScore")
    List<MovieDB> getRecommendationByYear(int year, int scoreStart, int scoreEnd);

    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie)<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and ID(m1)={1}  and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByAnotherMovieId(long userId, long movieId);

    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie )<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and m2.director = {1} and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByDirector(long userId, String reco);

    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie )<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and m2.year = {1} and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByYear(long userId, String reco);

    @Query("match(u1:User)-[w1:WATCHED]->(m1:Movie )<-[w:WATCHED]-(u2:User)-[w2:WATCHED]->(m2:Movie)where ID(u1)={0} and m2.type  = {1} and not (u1:User)-[:WATCHED]->(m2:Movie) return m2 as movie, avg(w2.score) as avgScore order by avgScore desc" )
    List<MovieDB> getRecommendationByType(long userId, String reco);

    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} return m as movie, avg(w.score) as avgScore")
    List<MovieDB> getWatchedMovies(long userId);
}