package hu.feketendras.splendex.movies.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import hu.feketendras.splendex.movies.application.MoviesApplication;
import hu.feketendras.splendex.movies.di.AppComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(MoviesApplication.getInjector());
    }

    public abstract void injectDependencies(AppComponent injector);
}
