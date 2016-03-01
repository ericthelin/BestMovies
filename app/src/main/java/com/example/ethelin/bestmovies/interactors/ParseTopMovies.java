package com.example.ethelin.bestmovies.interactors;

import com.example.ethelin.bestmovies.Constants;
import com.example.ethelin.bestmovies.models.TopMovie;
import com.example.ethelin.bestmovies.rx.UrlToJsonNodeFunc;
import com.fasterxml.jackson.databind.JsonNode;
import rx.Observable;
import rx.functions.Func1;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.example.ethelin.bestmovies.utility.NodeUtils.nodeToFloat;
import static com.example.ethelin.bestmovies.utility.NodeUtils.nodeToInt;
import static com.example.ethelin.bestmovies.utility.NodeUtils.nodeToString;

/**
 * Created by ethelin on 2/24/16.
 */
public class ParseTopMovies {

    private final UrlToJsonNodeFunc urlToJsonNodeFunc;

    @Inject
    public ParseTopMovies(UrlToJsonNodeFunc urlToJsonNodeFunc) {
        this.urlToJsonNodeFunc = urlToJsonNodeFunc;
    }

    public Observable<TopMovie> execute() {
        Observable<JsonNode> jsonNodeObservable = Observable.just(urlToJsonNodeFunc.call(Constants.Urls.TOP_MOVIES));

        return jsonNodeObservable
                .flatMap(new Func1<JsonNode, Observable<TopMovie>>() {

                    @Override
                    public Observable<TopMovie> call(final JsonNode root) {
                        List<TopMovie> movie = new ArrayList<>();
                        if (root != null) {
                            for (int i = 0; i < root.size(); i++) {
                                JsonNode movies = root.get(i);
                                if (movies != null) {
                                    TopMovie.Builder builder = new TopMovie.Builder();
                                    builder.setTitle(nodeToString(movies.get("title")));
                                    builder.setImdbId(nodeToString(movies.get("imdbId")));
                                    builder.setImdbLink(nodeToString(movies.get("imdbLink")));
                                    builder.setImdbRating(nodeToFloat(movies.get("imdbRating")));
                                    builder.setImdbVotes(nodeToInt(movies.get("imdbVotes")));
                                    builder.setPoster(nodeToString(movies.get("poster")));
                                    builder.setRank(nodeToInt(movies.get("rank")));
                                    builder.setYear(nodeToInt(movies.get("year")));
                                    builder.setPoster(nodeToString(movies.get("poster")));
                                    movie.add(builder.create());
                                }
                            }
                        }
                        return Observable.from(movie);
                    }
                });
    }

}
