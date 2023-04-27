package io.infinity.ratingsdataservice.service;

import io.infinity.ratingsdataservice.dto.Response;
import org.springframework.stereotype.Service;


@Service
public class RatingsDataService {

    public Response getUserRating(String userId) {
        Response response = new Response();
        response.initData(userId);
        return response;
    }

}
