package io.infinity.movieinfoservice.controller;

import io.infinity.movieinfoservice.dto.Movie;
import io.infinity.movieinfoservice.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Autowired
    private MovieInfoService service;

    @GetMapping("/{movieId}")
    public Movie movie(@PathVariable("movieId") int movieId) {
        return service.getMovie(movieId);
    }

}
