package com.example.ethelin.bestmovies.models;

/**
 * Created by ethelin on 2/24/16.
 */
public class MovieDetails {
    public final String imdbId;
    public final String imdbTitle;
    public final String title;
    public final float imdbRating;
    public final int imdbVotes;
    public final int year;
    public final String rated;
    public final String released;
    public final String runtime;
    public final String director;
    public final String[] genre;
    public final String writer;
    public final String[] actors;
    public final String plot;
    public final String[] language;
    public final String country;
    public final String awards;
    public final String poster;
    public final String metascore;

    public MovieDetails(String imdbId, String imdbTitle, String title, float imdbRating, int imdbVotes, int year, String rated, String released, String runtime, String director, String[] genre, String writer, String[] actors, String plot, String[] language, String country, String awards, String poster, String metascore) {
        this.imdbId = imdbId;
        this.imdbTitle = imdbTitle;
        this.title = title;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.director = director;
        this.genre = genre;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.metascore = metascore;
    }

    public static class Builder {
        private String imdbId;
        private String imdbTitle;
        private String title;
        private float imdbRating;
        private int imdbVotes;
        private int year;
        private String rated;
        private String released;
        private String runtime;
        private String director;
        private String[] genre;
        private String writer;
        private String[] actors;
        private String plot;
        private String[] language;
        private String country;
        private String awards;
        private String poster;
        private String metascore;

        public void setImdbId(String imdbId) {
            this.imdbId = imdbId;
        }

        public void setImdbTitle(String imdbTitle) {
            this.imdbTitle = imdbTitle;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImdbRating(float imdbRating) {
            this.imdbRating = imdbRating;
        }

        public void setImdbVotes(int imdbVotes) {
            this.imdbVotes = imdbVotes;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setRated(String rated) {
            this.rated = rated;
        }

        public void setReleased(String released) {
            this.released = released;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setGenre(String[] genre) {
            this.genre = genre;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public void setActors(String[] actors) {
            this.actors = actors;
        }

        public void setPlot(String plot) {
            this.plot = plot;
        }

        public void setLanguage(String[] language) {
            this.language = language;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setAwards(String awards) {
            this.awards = awards;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public void setMetascore(String metascore) {
            this.metascore = metascore;
        }

        public MovieDetails create() {
            return new MovieDetails(imdbId, imdbTitle, title, imdbRating, imdbVotes, year, rated, released, runtime, director, genre, writer, actors, plot, language, country, awards, poster, metascore);
        }
    }
}
