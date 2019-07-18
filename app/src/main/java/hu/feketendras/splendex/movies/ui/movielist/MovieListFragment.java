package hu.feketendras.splendex.movies.ui.movielist;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import hu.feketendras.splendex.movies.R;
import hu.feketendras.splendex.movies.di.AppComponent;
import hu.feketendras.splendex.movies.network.ApiError;
import hu.feketendras.splendex.movies.network.Movie;
import hu.feketendras.splendex.movies.ui.base.BaseFragment;

public class MovieListFragment extends BaseFragment {

    public static final String TAG = "MovieListFragment";

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Inject
    protected ViewModelProvider.Factory viewModelProviderFactory;

    @BindView(R.id.MovieListQueryEditText)
    protected EditText queryEditText;

    @BindView(R.id.MovieListRecyclerView)
    protected RecyclerView recyclerView;

    private MoviesListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    public void injectDependencies(AppComponent injector) {
        injector.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MovieListViewModel.class);
        viewModel.getMovieListLiveData().observe(this, movies -> handleMovieListResult(movies));
        viewModel.getMovieListErrorLiveData().observe(this, apiError -> handleMovieListError(apiError));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);
        adapter = new MoviesListAdapter(Picasso.get(), inflater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnTextChanged(R.id.MovieListQueryEditText)
    public void onQueryChanged(CharSequence query) {
        if (query.length() > 0) {
            viewModel.getMovieList(query.toString());
        }
    }

    private void handleMovieListResult(List<Movie> movies) {
        adapter.updateItems(movies);
    }

    private void handleMovieListError(ApiError apiError) {
        // TODO: handle error properly
        Toast.makeText(getContext(), R.string.error_general, Toast.LENGTH_SHORT).show();
    }

}
