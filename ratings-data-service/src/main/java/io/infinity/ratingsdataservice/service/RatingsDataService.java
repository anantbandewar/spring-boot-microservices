package io.infinity.ratingsdataservice.service;

import io.infinity.ratingsdataservice.dto.Rating;
import io.infinity.ratingsdataservice.dto.Response;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RatingsDataService {

    public Response getUserRating(String userId) {
        return new Response(Collections.singletonList(new Rating(userId, 100, 4)));
    }

}
