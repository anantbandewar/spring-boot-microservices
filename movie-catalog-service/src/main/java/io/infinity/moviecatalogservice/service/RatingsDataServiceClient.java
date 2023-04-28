package io.infinity.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.infinity.moviecatalogservice.dto.Rating;
import io.infinity.moviecatalogservice.dto.RatingsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RatingsDataServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings",
        // Optional second parameter
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        },

        // Bulkhead pattern implementation
        threadPoolKey = "ratingDataServicePool",
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "20"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
        }
    )
    public RatingsData getUserRatings(String userId) {
        return restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/users/" + userId, RatingsData.class);
    }

    public RatingsData getFallbackUserRatings(String userId) {
        return new RatingsData(userId, List.of(new Rating(0, 0)));
    }
}
