package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieDB;
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

    @Query("match(m:Movie),(u:User) where ID(m)={movieId} and ID(u)={userId} create (u)-[:WATCHED {score:  {userScore} }]->(m)")
    void evaluateMovie(@Param(value = "movieId") long id, @Param(value = "userScore") int userScore, @Param(value = "userId") long userId);

}