package com.whattowatch.WhatToWatch.controller;

import com.whattowatch.WhatToWatch.exception.IllegalSearchQueryException;
import com.whattowatch.WhatToWatch.javabean.Content;
import com.whattowatch.WhatToWatch.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ContentController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @GetMapping(value = "/search/{id}")
    public ModelAndView getContentImdbId(Model model, @PathVariable(name = "id") String imdbId) throws Exception {
        logger.debug("Entered getContentByImdbId()");
        logger.info("IMDb ID -> " + imdbId);
        Content content = contentService.getByIMDbId(URLEncoder.encode(imdbId, StandardCharsets.UTF_8));

        if (content == null) {
            throw new IllegalSearchQueryException("No result matched the search query");
        }

        model.addAttribute("title", content.getTitle());
        model.addAttribute("content", content);
        logger.debug("Leaving getContentByImdbId()");
        return new ModelAndView("content");
    }

    @GetMapping(value = "/search")
    public ModelAndView getContentByQuery(Model model, @RequestParam(name = "term", required = false, defaultValue = "") String query) throws Exception {
        logger.debug("Entered getContentByQuery()");

        if (query.matches("^tt[\\d]{7}$")) {
            getContentImdbId(model, query);
            return new ModelAndView("content");
        }

        List<Content> shows = contentService.getByQuery(URLEncoder.encode(query, StandardCharsets.UTF_8));

        if (shows == null) {
            throw new IllegalSearchQueryException("No result matched the search query");
        }

        model.addAttribute("term", query);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPageCount", shows.get(0).getPageCount());
        logger.debug("Leaving getContentByQuery()");
        return new ModelAndView("searchview");
    }

    @GetMapping(value = "/search", params = {"page"})
    public ModelAndView navigatePage(Model model, @RequestParam(name = "term") String query,
                                     @RequestParam(name = "page", defaultValue = "1") Integer pageNumber) throws Exception {
        logger.debug("Entered navigatePage()");
        logger.info("Page -> " + pageNumber);
        List<Content> shows = contentService.getByQueryIncludingPage(URLEncoder.encode(query.trim(), StandardCharsets.UTF_8), pageNumber);

        if (shows == null) {
            throw new IllegalSearchQueryException("No such page");
        }

        model.addAttribute("term", query);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPageCount", shows.get(0).getPageCount());
        logger.debug("Leaving navigatePage()");
        return new ModelAndView("searchview");
    }

    @GetMapping(value = "/contentNamesAutocomplete")
    public @ResponseBody List<String> autocompleteContentName(@RequestParam(name = "term", defaultValue = "", required = false) String searchTerm) throws Exception {
        logger.debug("entered autocompleteContentName()");
        logger.info("Query -> " + searchTerm);
        List<String> suggestions = new ArrayList<>();
        List<Content> shows = contentService.getByQuery(URLEncoder.encode(searchTerm, StandardCharsets.UTF_8));

        if (shows != null) {
            for (Content show : shows) {
                suggestions.add(show.toString());
            }
        }
        return suggestions;
    }
}
