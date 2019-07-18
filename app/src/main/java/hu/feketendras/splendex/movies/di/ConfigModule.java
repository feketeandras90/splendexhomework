package hu.feketendras.splendex.movies.di;

import android.content.Context;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;
import hu.feketendras.splendex.movies.R;

@Module
public abstract class ConfigModule {

    @Provides
    @MoviesApiBaseUrl
    public static String moviesApiBase(Context context) {
        return context.getString(R.string.config_movies_api_base_url);
    }

    @Provides
    @MoviesApiKey
    public static String apiKey(Context context) {
        return context.getString(R.string.config_movies_api_key);
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MoviesApiBaseUrl {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MoviesApiKey {
    }
}
