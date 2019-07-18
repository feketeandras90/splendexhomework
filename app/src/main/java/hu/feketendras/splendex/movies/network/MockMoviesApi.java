package hu.feketendras.splendex.movies.network;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class MockMoviesApi implements MoviesApi {

    @Inject
    public MockMoviesApi() {
    }

    @Override
    public List<Movie> getMovies() {
        return Arrays.asList(new Movie("Spiderman"), new Movie("Batman"), new Movie("Superman"));
    }
}
