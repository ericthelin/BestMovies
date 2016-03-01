package com.example.ethelin.bestmovies.interactors;

import com.example.ethelin.bestmovies.Constants;
import com.example.ethelin.bestmovies.models.MovieDetails;
import com.example.ethelin.bestmovies.rx.UrlToJsonNodeFunc;
import com.fasterxml.jackson.databind.JsonNode;
import rx.Observable;
import rx.functions.Func1;

import javax.inject.Inject;
import java.util.Locale;

import static com.example.ethelin.bestmovies.utility.NodeUtils.nodeToFloat;
import static com.example.ethelin.bestmovies.utility.NodeUtils.nodeToInt;
import static com.example.ethelin.bestmovies.utility.NodeUtils.nodeToString;

/**
 * Created by ethelin on 2/24/16.
 */
public class ParseMovieDetails {
    private final UrlToJsonNodeFunc urlToJsonNodeFunc;

    @Inject
    public ParseMovieDetails(UrlToJsonNodeFunc urlToJsonNodeFunc) {
        this.urlToJsonNodeFunc = urlToJsonNodeFunc;
    }

    public Observable<MovieDetails> execute(String imdbId) {
        Observable<JsonNode> jsonNodeObservable = Observable.just(urlToJsonNodeFunc.call(String.format(Locale.US, Constants.Urls.MOVIE_DETAILS, imdbId)));

        return jsonNodeObservable
                .flatMap(new Func1<JsonNode, Observable<MovieDetails>>() {

                    @Override
                    public Observable<MovieDetails> call(final JsonNode movie) {
                        if (movie != null) {
                            MovieDetails.Builder builder = new MovieDetails.Builder();
                            builder.setTitle(nodeToString(movie.get("title")));
                            builder.setImdbId(nodeToString(movie.get("imdbId")));
                            builder.setTitle(nodeToString(movie.get("title")));
                            builder.setImdbRating(nodeToFloat(movie.get("imdbRating")));
                            builder.setImdbVotes(nodeToInt(movie.get("imdbVotes")));
                            builder.setPoster(nodeToString(movie.get("poster")));
                            builder.setYear(nodeToInt(movie.get("year")));
                            builder.setReleased(nodeToString(movie.get("released")));
                            builder.setPlot(nodeToString(movie.get("plot")));
                            builder.setRuntime(nodeToString(movie.get("runtime")));
                            builder.setRated(nodeToString(movie.get("rated")));
//                                    builder.setGenre(nodeToString(movie.get("genre")));
                            builder.setWriter(nodeToString(movie.get("writer")));
                            builder.setDirector(nodeToString(movie.get("director")));
//                                    builder.setActors(nodeToString(movie.get("actors")));
//                                    builder.setCountry(movie.get("country")););
//                                    builder.setLanguage(nodeToString(movie.get("language")));
                            builder.setAwards(nodeToString(movie.get("metascore")));
                            builder.setMetascore(nodeToString(movie.get("metascore")));
                            MovieDetails movieDetails = builder.create();
                            return Observable.just(movieDetails);
                        }
                        return null;
                    }
                });
    }

}
