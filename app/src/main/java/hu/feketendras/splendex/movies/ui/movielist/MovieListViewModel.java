package hu.feketendras.splendex.movies.ui.movielist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import hu.feketendras.splendex.movies.network.Movie;
import hu.feketendras.splendex.movies.network.MoviesApi;

public class MovieListViewModel extends ViewModel {

    private MoviesApi api;

    private MutableLiveData<List<Movie>> movieListLiveData;

    @Inject
    public MovieListViewModel(MoviesApi api) {
        this.api = api;
        this.movieListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public void getMovieList() {
        movieListLiveData.postValue(api.getMovies());
    }
}
