package com.whattowatch.WhatToWatch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whattowatch.WhatToWatch.javabean.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ContentService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    Scanner scanner;
    String result;
    JsonNode node;

    public Content getByIMDbId(String id) throws Exception {
        logger.debug("Entered getByIMDbId()");
        Content content = new Content();
        URL url = new URL("http://www.omdbapi.com/?apikey=293e7528&plot=full&i=" + id);

        JsonNode node = getJsonNode(url);

        if (node == null) {
            return null;
        }

        content.setTitle(node.get("Title").asText("N/A"));
        content.setYear(node.get("Year").asText("N/A"));
        content.setRated(node.get("Rated").asText("N/A"));
        content.setReleaseDate(node.get("Released").asText("N/A"));
        content.setRuntime(node.get("Runtime").asText("N/A")); 
        content.setGenre(node.get("Genre").asText("N/A"));
        content.setDirector(node.get("Director").asText("N/A"));
        content.setWriter(node.get("Writer").asText("N/A")); 
        content.setActors(node.get("Actors").asText("N/A")); 
        content.setPlot(node.get("Plot").asText("N/A"));
        content.setAwards(node.get("Awards").asText("N/A")); 
        content.setPoster(node.get("Poster").asText("N/A"));
        content.setMetascore(node.get("Metascore").asText("N/A"));
        content.setImdbRating(node.get("imdbRating").asText("N/A"));
        content.setRottenTomatoesRating("N/A");
        content.setImdbId(node.get("imdbID").asText());
        content.setType(node.get("Type").asText());

        if (node.get("Poster").asText().equals("N/A")) {
            content.setPoster(null);
        }

        for (int i = 0; i < node.get("Ratings").size(); i++) {
            if (node.get("Ratings").get(i).get("Source").asText().equals("Rotten Tomatoes")) {
                content.setRottenTomatoesRating(node.get("Ratings").get(i).get("Value").asText());
                break;
            }
        }

        logger.debug("Leaving getByIMDbId()");
        return content;
    }

    public List<Content> getByQuery(String query) throws Exception {
        logger.debug("Entered getByQuery()");
        List<Content> contentList = new ArrayList<>();
        URL url = new URL("https://www.omdbapi.com/?apikey=293e7528&plot=full&page=1&s=" + query);
        return getContents(contentList, url);
    }

    public List<Content> getByQueryIncludingPage(String query, Integer page) throws Exception {
        logger.debug("Entered getByQueryIncludingPage()");
        List<Content> contentList = new ArrayList<>();
        URL url = new URL("https://www.omdbapi.com/?apikey=293e7528&plot=full&page=" + page + "&s=" + query);
        return getContents(contentList, url);
    }

    private List<Content> getContents(List<Content> contentList, URL url) throws Exception {
        JsonNode node = getJsonNode(url);
        Double pageCount;

        if (node == null) {
            return null;
        }

        for (int i = 0; i < node.get("Search").size(); i++) {
            contentList.add(new Content());
            contentList.get(i).setTitle(node.get("Search").get(i).get("Title").asText());
            contentList.get(i).setReleaseDate(node.get("Search").get(i).get("Year").asText());
            contentList.get(i).setImdbId(node.get("Search").get(i).get("imdbID").asText());
            contentList.get(i).setType(node.get("Search").get(i).get("Type").asText());
            if (node.get("Search").get(i).get("Poster").asText().equals("N/A")) {
//                contentList.get(i).setPoster("https://www.clipartmax.com/png/small/230-2305637_movie-popcorn-clipart-no-background-popcorn-clip-art.png");
                contentList.get(i).setPoster(null);
            } else {
                contentList.get(i).setPoster(node.get("Search").get(i).get("Poster").asText());
            }
        }

        pageCount = node.get("totalResults").asDouble() / 10;

        System.out.println(contentList);
        contentList.get(0).setPageCount((int) Math.ceil(pageCount));

        logger.debug("Leaving getByQuery()");
        return contentList;
    }

    private JsonNode getJsonNode(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            result = scanner.nextLine();
        }

        ObjectMapper mapper = new ObjectMapper();
        node = mapper.readTree(result);
        System.out.println(node.toPrettyString());

        if (node.has("Error")) {
            return null;
        }

        scanner.close();
        return node;
    }
}
