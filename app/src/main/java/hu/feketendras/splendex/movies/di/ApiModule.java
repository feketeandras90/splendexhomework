package hu.feketendras.splendex.movies.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.feketendras.splendex.movies.BuildConfig;
import hu.feketendras.splendex.movies.network.MoviesApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ApiModule {

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isStethoEnabled()) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    @Provides
    public static Retrofit.Builder retrofitBuilder(OkHttpClient client) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client);
    }

    @Provides
    @Singleton
    public static MoviesApi moviesApi(Retrofit.Builder builder, @ConfigModule.MoviesApiBaseUrl String moviesApiBaseUrl) {
        return builder.baseUrl(moviesApiBaseUrl).build().create(MoviesApi.class);
    }

    private static boolean isStethoEnabled() {
        return BuildConfig.DEBUG;
    }

}
