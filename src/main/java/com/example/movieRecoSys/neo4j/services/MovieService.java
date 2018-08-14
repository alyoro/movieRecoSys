package com.example.movieRecoSys.neo4j.services;

        import com.example.movieRecoSys.neo4j.domain.MovieUI;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public interface MovieService {

        List<MovieUI> getTopMovies();
}
