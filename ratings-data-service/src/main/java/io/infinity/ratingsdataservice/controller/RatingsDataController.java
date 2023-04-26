package io.infinity.ratingsdataservice.controller;

import io.infinity.ratingsdataservice.dto.Rating;
import io.infinity.ratingsdataservice.service.RatingsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsDataController {

    @Autowired
    private RatingsDataService service;

    @GetMapping("/users/{userId}")
    public List<Rating> ratings(@PathVariable("userId") int userId) {
        return service.getUserRating(userId);
    }

}
