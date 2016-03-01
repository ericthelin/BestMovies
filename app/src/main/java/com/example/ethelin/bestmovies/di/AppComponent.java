package com.example.ethelin.bestmovies.di;

import com.example.ethelin.bestmovies.views.DetailActivity;
import com.example.ethelin.bestmovies.views.TopMoviesActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(TopMoviesActivity target);

    void inject(DetailActivity target);
}