package io.infinity.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.infinity.moviecatalogservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCatalogService {

    @Autowired
    RatingsDataServiceClient ratingsDataServiceClient;

    @Autowired
    MovieInfoServiceClient movieInfoServiceClient;

    public Response getCatalog(String userId) {

        Response response = new Response(userId, new ArrayList<>());
        RatingsData ratingsData = ratingsDataServiceClient.getUserRatings(userId);

        response.setMovies(
            ratingsData.getRatings().stream().map(movieInfoServiceClient::getMovieDetails).collect(Collectors.toList())
        );

        return response;
    }

}
