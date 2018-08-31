package com.example.movieRecoSys.service;


import com.example.movieRecoSys.neo4j.domain.Movie;
import com.example.movieRecoSys.neo4j.domain.MovieDB;
import com.example.movieRecoSys.neo4j.domain.MovieUI;
import com.example.movieRecoSys.neo4j.services.MovieService;
import lombok.var;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MovieServiceImplTest {

    @Mock
    MovieService movieService;

    @Test
    public void testGetRecommendations(){
        var movie = new MovieUI(1L, "testTitle", "testDir", "2000", "Drama", 9.2, 8.0);
        var movieUIList = new ArrayList<MovieUI>();
        movieUIList.add(movie);

        when(movieService.getRecommendations("type","Drama")).thenReturn(movieUIList);

        List<MovieUI> movieUI = movieService.getRecommendations("type", "Drama");

        Assert.assertEquals("testDir", movieUI.get(0).getDirector());
        Assert.assertEquals("testTitle", movieUI.get(0).getTitle());
        Assert.assertEquals("Drama", movieUI.get(0).getType());
        Assert.assertEquals("2000", movieUI.get(0).getYear());
    }

    @Test
    public void testGetTopMovies(){
        var movieUIList = new ArrayList<MovieUI>();
        movieUIList.add(new MovieUI(1L, "testTitle", "testDir", "2000", "Drama", 9.2, 8.0));
        movieUIList.add(new MovieUI(2L, "testTitle2", "testDir2", "2000", "Drama", 9.0, 7.0));
        movieUIList.add(new MovieUI(3L, "testTitle3", "testDir3", "1999", "Comedy", 7.8, 9.0));
        movieUIList.add(new MovieUI(4L, "testTitle4", "testDir4", "1998", "Drama", 5.0, 4.0));

        when(movieService.getTopMovies()).thenReturn(movieUIList);

        List<MovieUI> movieUI = movieService.getTopMovies();

        Assert.assertEquals(4, movieUI.size());
        Assert.assertEquals("testDir3", movieUI.get(2).getDirector());
        Assert.assertEquals((Double)9.0, movieUI.get(1).getAvgScore());
        Assert.assertEquals((Long)1L, movieUI.get(0).getId());
    }
}