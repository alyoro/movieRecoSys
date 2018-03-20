package com.example.movieRecoSys;

import com.example.movieRecoSys.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRepoTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void getMoviesDataById() {
        Long id = 2L;
        System.out.println("TEST+______________:" + movieRepository.getMoviesDataById(id).getAvgScore());
    }

    @Test
    public void getMovieDataByTitleTest() {
        String title = "The Shawshank Redemption";
        System.out.println("TEST+______________:" + movieRepository.getMoviesDataByTitle(title).getAvgScore());
    }
}

