package io.infinity.moviecatalogservice.service;

import io.infinity.moviecatalogservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    public Response getCatalog(String userId) {

        Response response = new Response(userId, new ArrayList<>());
        RatingsData ratingsData = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/users/" + userId, RatingsData.class);

        response.setMovies(
            ratingsData.getRatings().stream().map(rating -> {
                Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
                return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
            }).collect(Collectors.toList())
        );

        return response;
    }

}
