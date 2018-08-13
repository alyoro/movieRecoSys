package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieUI;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    List<Movie> findMovieByTitle(String title);

    @Query("match(:User)-[n:WATCHED]->(m:Movie) where ID(m)={0} return m as movie, avg(n.score) as avgScore")
    MovieUI getMoviesDataById(Long id);

    @Query("match(:User)-[n:WATCHED]->(m:Movie) where m.title={0} return m as movie, avg(n.score) as avgScore")
    MovieUI getMoviesDataByTitle(String title);

    @Query("match(:User)-[n:WATCHED]->(m:Movie) return m as movie, avg(n.score) as avgScore order by avgScore desc")
    List<MovieUI> getTopMovies();

}