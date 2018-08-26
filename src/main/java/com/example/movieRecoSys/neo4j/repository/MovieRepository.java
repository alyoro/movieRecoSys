package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieDB;
import com.example.movieRecoSys.neo4j.domain.Watched;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {


    @Query("match(:User)-[n:WATCHED]->(m:Movie) where ID(m)={0} return m as movie, avg(n.score) as avgScore")
    MovieDB getMoviesById(Long id);

    @Query("match(:User)-[n:WATCHED]->(m:Movie) where m.title={0} return m as movie, avg(n.score) as avgScore")
    List<MovieDB> getMoviesByTitle(String title);

    @Query("match(:User)-[n:WATCHED]->(m:Movie) return m as movie, avg(n.score) as avgScore order by avgScore desc")
    List<MovieDB> getTopMovies();

    @Query("match(m:Movie),(u:User) where ID(u)={0} and ID(m)={1} create (u)-[:WATCHED {score:  {2} }]->(m)")
    void evaluateMovie(long userId, long movieId, int userScore);

    @Query("match(u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} set w.score = {2}")
    void changeEvaluation(long userId, long movieId, int userScore);

    @Query("match (u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} return w")
    Watched checkIfThereIsRelationshipInDBAlready(long userId, long movieId);


}