package com.whattowatch.WhatToWatch.javabean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Content {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRottenTomatoesRating() {
        return rottenTomatoesRating;
    }

    public void setRottenTomatoesRating(String rottenTomatoesRating) {
        this.rottenTomatoesRating = rottenTomatoesRating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty(value = "Title")
    String title;

    @JsonProperty(value = "Year")
    String year;

    @JsonProperty(value = "Rated")
    String rated;

    @JsonProperty(value = "Released")
    String releaseDate;

    @JsonProperty(value = "Runtime")
    String runtime;

    @JsonProperty(value = "Genre")
    String genre;

    @JsonProperty(value = "Director")
    String director;

    @JsonProperty(value = "Writer")
    String writer;

    @JsonProperty(value = "Actors")
    String actors;

    @JsonProperty(value = "Plot")
    String plot;

    @JsonProperty(value = "Awards")
    String awards;

    @JsonProperty(value = "Poster")
    String poster;

    @JsonProperty(value = "Metascore")
    String metascore;

    @JsonProperty(value = "imdbRating")
    String imdbRating;

    @JsonProperty(value = "Rotten Tomatoes")
    String rottenTomatoesRating;

    @JsonProperty(value = "IMDb ID")
    String imdbId;

    @JsonProperty(value = "Type")
    String type;

    @JsonProperty(value = "PageCount")
    Integer pageCount;

    @Override
    public String toString() {
        return title;
    }
}
