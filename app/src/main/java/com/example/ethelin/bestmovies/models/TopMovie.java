package com.example.ethelin.bestmovies.models;

/**
 * Created by ethelin on 2/24/16.
 */
public class TopMovie {
    public final int rank;
    public final String title;
    public final int year;
    public final String imdbId;
    public final float imdbRating;
    public final int imdbVotes;
    public final String imdbLink;
    public final String poster;

    public TopMovie(int rank, String title, int year, String imdbId, float imdbRating, int imdbVotes, String imdbLink, String poster) {
        this.rank = rank;
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbLink = imdbLink;
        this.poster = poster;
    }

    public static class Builder {
        private int rank;
        private String title;
        private int year;
        private String imdbId;
        private float imdbRating;
        private int imdbVotes;
        private String imdbLink;
        private String poster;

        public Builder() {
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setImdbId(String imdbId) {
            this.imdbId = imdbId;
        }

        public void setImdbRating(float imdbRating) {
            this.imdbRating = imdbRating;
        }

        public void setImdbVotes(int imdbVotes) {
            this.imdbVotes = imdbVotes;
        }

        public void setImdbLink(String imdbLink) {
            this.imdbLink = imdbLink;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public TopMovie create() {
            return new TopMovie(rank, title, year, imdbId, imdbRating, imdbVotes, imdbLink, poster);
        }
    }
}
