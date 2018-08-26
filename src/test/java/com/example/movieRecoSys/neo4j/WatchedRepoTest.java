package com.example.movieRecoSys.neo4j;

import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.User;
import com.example.movieRecoSys.neo4j.repository.MovieRepository;
import com.example.movieRecoSys.neo4j.repository.UserRepository;
import com.example.movieRecoSys.neo4j.repository.WatchedRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WatchedRepoTest {

    @Autowired
    WatchedRepository watchedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;



//    @Test
//    public void fetchWatchedTest(){
//        User user = userRepository.findByUsername("test6");
//        Movie movie = movieRepository.getMoviesById(2L).getMovie();
//        log.info(watchedRepository.findByStartNodeAndEndNode(user,movie).get(0));
//    }
}
