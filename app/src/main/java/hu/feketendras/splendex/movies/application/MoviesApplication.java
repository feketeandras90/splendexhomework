package hu.feketendras.splendex.movies.application;

import android.app.Application;

import hu.feketendras.splendex.movies.di.AppComponent;
import hu.feketendras.splendex.movies.di.DaggerAppComponent;

public class MoviesApplication extends Application {

    private static AppComponent injector;

    public static AppComponent getInjector() {
        return injector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerAppComponent.builder().context(this).build();
    }
}
