package hu.feketendras.splendex.movies.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("search/movie")
    Call<MovieSearchResponse> getMovies(@Query("query") String query, @Query("api_key") String apiKey);

    @GET("movie/{movieId}")
    Call<Movie> getMovieDetials(@Path("movieId") int movieId, @Query("api_key") String apiKey);
}
