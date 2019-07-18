package hu.feketendras.splendex.movies.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import hu.feketendras.splendex.movies.ui.movielist.MovieListFragment;

@Singleton
@Component(modules = {ConfigModule.class, ApiModule.class, ViewModelModule.class})
public interface AppComponent {

    void inject(MovieListFragment movieListFragment);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }
}
