package io.infinity.movieinfoservice.service;

import io.infinity.movieinfoservice.dto.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieInfoService {

    public Movie getMovie(int movieId) {
        return new Movie(movieId, "The Night Manager", "The story of a night manager");
    }

}
