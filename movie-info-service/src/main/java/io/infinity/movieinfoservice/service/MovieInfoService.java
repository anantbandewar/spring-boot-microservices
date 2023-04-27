package io.infinity.movieinfoservice.service;

import io.infinity.movieinfoservice.dto.MdbResponse;
import io.infinity.movieinfoservice.dto.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    public Movie getMovie(int movieId) {
        MdbResponse response = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MdbResponse.class);
        return new Movie(movieId, response.getTitle(), response.getOverview());
    }

}
