package hu.feketendras.splendex.movies.ui.movielist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import hu.feketendras.splendex.movies.di.ConfigModule;
import hu.feketendras.splendex.movies.network.ApiError;
import hu.feketendras.splendex.movies.network.Movie;
import hu.feketendras.splendex.movies.network.MovieSearchResponse;
import hu.feketendras.splendex.movies.network.MoviesApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private final MoviesApi api;
    private final String apiKey;

    private final MutableLiveData<List<Movie>> movieListLiveData;
    private final MutableLiveData<ApiError> movieListErrorLiveData;

    @Inject
    public MovieListViewModel(MoviesApi api, @ConfigModule.MoviesApiKey String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
        this.movieListLiveData = new MutableLiveData<>();
        this.movieListErrorLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public MutableLiveData<ApiError> getMovieListErrorLiveData() {
        return movieListErrorLiveData;
    }

    public void getMovieList() {
        api.getMovies("a", apiKey).enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.isSuccessful()) {
                    movieListLiveData.postValue(response.body().results);
                } else {
                    handleErrorResponse(response);
                }
            }

            private void handleErrorResponse(Response<MovieSearchResponse> response) {
                String errorJson = null;
                ResponseBody errorBody = response.errorBody();
                if (errorBody != null) {
                    try {
                        errorJson = errorBody.string();
                     } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                movieListErrorLiveData.postValue(new ApiError(errorJson));
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                movieListErrorLiveData.postValue(new ApiError(t));
            }
        });
    }
}
