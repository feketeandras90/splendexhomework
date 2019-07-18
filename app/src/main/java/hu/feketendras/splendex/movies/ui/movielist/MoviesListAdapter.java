package hu.feketendras.splendex.movies.ui.movielist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.feketendras.splendex.movies.R;
import hu.feketendras.splendex.movies.network.Movie;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {

    private final Picasso picasso;
    private final LayoutInflater inflater;
    private final List<Movie> movieList;

    public MoviesListAdapter(Picasso picasso, LayoutInflater inflater) {
        this.picasso = picasso;
        this.inflater = inflater;
        this.movieList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = inflater.inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int position) {
        Movie movie = movieList.get(position);
        String url = "http://image.tmdb.org/t/p/w185/" + movie.getPoster_path();
        picasso.load(url).into(viewHolder.posterImageView);
        viewHolder.titleTextView.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateItems(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.MovieItemPoster)
        protected ImageView posterImageView;

        @BindView(R.id.MovieItemTitle)
        protected TextView titleTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
