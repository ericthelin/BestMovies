package com.example.ethelin.bestmovies.views;

import android.util.Log;
import com.example.ethelin.bestmovies.interactors.GetTopMovies;
import com.example.ethelin.bestmovies.models.TopMovie;
import com.example.ethelin.bestmovies.utility.NullObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ethelin on 2/23/16.
 */
public class TopMoviesPresenter {

    private static final TopMoviesView NULL_VIEW = NullObject.create(TopMoviesView.class);

    private TopMoviesView view = NULL_VIEW;

    private final GetTopMovies getTopMovies;

    @Inject
    public TopMoviesPresenter(GetTopMovies getTopMovies) {
        this.getTopMovies = getTopMovies;
    }


    public void present() {
//        view.setOverflowClickListener(new HomeOverflowClickListener() {
//            @Override
//            public void onSettingsClicked() {
//                view.launchSettings();
//            }
//
//            @Override
//            public void onPrivacyPolicyClicked() {
//                view.launchPrivacyPolicy();
//            }
//
//            @Override
//            public void onFeedbackClicked() {
//                view.launchFeedback();
//            }
//        });
        reload();
    }

    public void attach(TopMoviesView view) {
        this.view = view;
    }

    public void detach() {
        this.view = NULL_VIEW;
    }

//    @Override
//    public void shouldLaunchActivity(Intent intent) {
//        view.shouldStartActivityWithIntent(intent);
//    }

//    @Override
//    public void itemSelected(Object object) {
//        //empty method stub
//    }

    public void reload() {
        Log.w("EKT", "TopMoviesPresentor reload calledD");
        getTopMovies
                .all()
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TopMovie>>() {

                    @Override
                    public void call(List<TopMovie> movies) {
                        if (movies == null) {
                            Log.w("EKT", "TopMoviesPresentor reload FAILED");
                            return;
                        }
                        Log.w("EKT", "TopMoviesPresenter reload.getTopMovies : " + movies.size());
                        view.setMovies(movies);
                    }
                });
    }

    interface TopMoviesView {
        void setMovies(List<TopMovie> movies);

    }

    interface HomeOverflowClickListener {
        void onSettingsClicked();

        void onPrivacyPolicyClicked();

        void onFeedbackClicked();
    }
}
