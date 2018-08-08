package com.example.movieRecoSys.neo4j;

import com.example.movieRecoSys.neo4j.repository.MovieRepository;
import org.junit.Assert;
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
    public void checkConnectionToNeo4jAndSampleData() {
        String title = "The Shawshank Redemption";
        String year = "1995";
        Assert.assertEquals(year,movieRepository.getMoviesDataByTitle(title).getMovie().getYear());
    }
}

