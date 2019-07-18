package hu.feketendras.splendex.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hu.feketendras.splendex.movies.ui.movielist.MovieListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.MainFragmentContainer, MovieListFragment.newInstance(), MovieListFragment.TAG)
                    .commit();
        }
    }
}
