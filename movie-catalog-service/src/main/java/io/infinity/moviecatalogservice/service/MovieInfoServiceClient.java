package io.infinity.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.infinity.moviecatalogservice.dto.CatalogItem;
import io.infinity.moviecatalogservice.dto.Movie;
import io.infinity.moviecatalogservice.dto.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieDetails",
        // Optional second parameter
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        },

        // Bulkhead pattern implementation
        threadPoolKey = "movieInfoServicePool",
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "20"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
        }
    )
    public CatalogItem getMovieDetails(Rating rating) {
        Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(rating.getMovieId(), movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackMovieDetails(Rating rating) {
        return new CatalogItem(rating.getMovieId(), "No movie", "", rating.getRating());
    }
}
