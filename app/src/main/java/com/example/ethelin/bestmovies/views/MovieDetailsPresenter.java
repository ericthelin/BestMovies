package com.example.ethelin.bestmovies.views;

import com.example.ethelin.bestmovies.interactors.GetMovieDetails;
import com.example.ethelin.bestmovies.models.MovieDetails;
import com.example.ethelin.bestmovies.utility.NullObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

/**
 * Created by ethelin on 2/23/16.
 */
public class MovieDetailsPresenter {

    private static final MovieDetailsView NULL_VIEW = NullObject.create(MovieDetailsView.class);

    private MovieDetailsView view = NULL_VIEW;

    private final GetMovieDetails getMovieDetails;

    @Inject
    public MovieDetailsPresenter(GetMovieDetails getMovieDetails) {
        this.getMovieDetails = getMovieDetails;
    }


    public void present(String imdbId) {
        getMovieDetails
                .getById(imdbId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetails>() {
                    @Override
                    public void call(MovieDetails movieDetails) {
                        view.setMovieDetails(movieDetails);
                    }
                });
    }

    public void attach(MovieDetailsView view) {
        this.view = view;
    }

    public void detach() {
        this.view = NULL_VIEW;
    }

    interface MovieDetailsView {
        void setMovieDetails(MovieDetails movieDetails);
    }
}
