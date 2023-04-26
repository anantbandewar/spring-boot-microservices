package io.infinity.moviecatalogservice.controller;

import io.infinity.moviecatalogservice.dto.CatalogItem;
import io.infinity.moviecatalogservice.dto.Response;
import io.infinity.moviecatalogservice.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private MovieCatalogService service;

    @GetMapping("/{userId}")
    public Response catalog(@PathVariable("userId") String userId) {
        return service.getCatalog(userId);
    }

}
