package hu.feketendras.splendex.movies.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import hu.feketendras.splendex.movies.ui.movielist.MovieListViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    public abstract ViewModelProvider.Factory viewModelProviderFactory(MoviesViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    public abstract ViewModel movieListViewModel(MovieListViewModel viewModel);
}
