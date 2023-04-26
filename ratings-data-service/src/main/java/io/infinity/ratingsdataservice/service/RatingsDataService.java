package io.infinity.ratingsdataservice.service;

import io.infinity.ratingsdataservice.dto.Rating;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RatingsDataService {

    public List<Rating> getUserRating(int userId) {
        return Collections.singletonList(new Rating(userId, 100, 4));
    }

}
