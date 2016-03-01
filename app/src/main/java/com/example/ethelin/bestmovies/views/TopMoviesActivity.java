package com.example.ethelin.bestmovies.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.ethelin.bestmovies.R;
import com.example.ethelin.bestmovies.ReferenceApplication;
import com.example.ethelin.bestmovies.models.TopMovie;

import javax.inject.Inject;
import java.util.List;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesPresenter.TopMoviesView {

    @Inject TopMoviesPresenter presenter;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    private TopMoviesAdapter topMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        ReferenceApplication.component().inject(this);
        ButterKnife.bind(this);

        presenter.attach(this);
        presenter.present();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topMoviesAdapter = new TopMoviesAdapter(this);
        recyclerView.setAdapter(topMoviesAdapter);

    }

    @Override
    public void setMovies(List<TopMovie> topMovies) {
        topMoviesAdapter.setMovies(topMovies);
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
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
        ButterKnife.unbind(this);
    }
}
