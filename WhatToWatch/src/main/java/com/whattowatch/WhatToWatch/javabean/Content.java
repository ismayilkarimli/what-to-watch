package com.whattowatch.WhatToWatch.javabean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Content {

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
