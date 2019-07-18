package hu.feketendras.splendex.movies.di;

import dagger.Binds;
import dagger.Module;
import hu.feketendras.splendex.movies.network.MockMoviesApi;
import hu.feketendras.splendex.movies.network.MoviesApi;

@Module
public abstract class ApiModule {

    @Binds
    public abstract MoviesApi moviesApi(MockMoviesApi moviesApi);
}
