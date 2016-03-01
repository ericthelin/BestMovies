package com.example.ethelin.bestmovies.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.ethelin.bestmovies.R;
import com.example.ethelin.bestmovies.ReferenceApplication;
import com.example.ethelin.bestmovies.models.MovieDetails;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity implements MovieDetailsPresenter.MovieDetailsView {
    public static final String EXTRA_IMDB_ID = "extra:imdb_id";

    @Inject MovieDetailsPresenter presenter;
    @Bind(R.id.poster) ImageView poster;
    @Bind(R.id.imdbId) TextView imdbId;
    @Bind(R.id.imdbTitle) TextView imdbTitle;
    @Bind(R.id.imdbRating) TextView imdbRating;
    @Bind(R.id.imdbVotes) TextView imdbVotes;
    @Bind(R.id.year) TextView year;
    @Bind(R.id.rated) TextView rated;
    @Bind(R.id.released) TextView released;
    @Bind(R.id.runtime) TextView runtime;
    @Bind(R.id.director) TextView director;
    @Bind(R.id.genre) TextView genre;
    @Bind(R.id.writer) TextView writer;
    @Bind(R.id.actors) TextView actors;
    @Bind(R.id.plot) TextView plot;
    @Bind(R.id.language) TextView language;
    @Bind(R.id.country) TextView country;
    @Bind(R.id.awards) TextView awards;
    @Bind(R.id.title) TextView title;
    @Bind(R.id.metascore) TextView metascore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ReferenceApplication.component().inject(this);
        ButterKnife.bind(this);

        presenter.attach(this);

        if (getIntent().hasExtra(EXTRA_IMDB_ID)) {
            presenter.present(getIntent().getStringExtra(EXTRA_IMDB_ID));
        }



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMovieDetails(MovieDetails movieDetails) {
        Picasso.with(this).load(movieDetails.poster).into(poster);
        imdbId.setText(String.valueOf(movieDetails.imdbId));
        imdbTitle.setText(String.valueOf(movieDetails.imdbTitle));
        imdbRating.setText(String.valueOf(movieDetails.imdbRating));
        imdbVotes.setText(String.valueOf(movieDetails.imdbVotes));
        rated.setText(String.valueOf(movieDetails.rated));
        released.setText(String.valueOf(movieDetails.released));
        runtime.setText(String.valueOf(movieDetails.released));
        director.setText(String.valueOf(movieDetails.director));
//        genre.setText(String.valueOf(movieDetails.genre));
        writer.setText(String.valueOf(movieDetails.writer));
//        actors.setText(String.valueOf(movieDetails.actors));
        plot.setText(String.valueOf(movieDetails.plot));
//        language.setText(String.valueOf(movieDetails.language));
        country.setText(String.valueOf(movieDetails.country));
        awards.setText(String.valueOf(movieDetails.awards));
        title.setText(String.valueOf(movieDetails.title));
        metascore.setText(String.valueOf(movieDetails.metascore));
        year.setText(String.valueOf(movieDetails.year));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
        ButterKnife.unbind(this);
    }

}
