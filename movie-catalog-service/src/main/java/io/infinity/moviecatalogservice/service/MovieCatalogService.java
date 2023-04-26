package io.infinity.moviecatalogservice.service;

import io.infinity.moviecatalogservice.dto.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieCatalogService {

    public List<CatalogItem> getCatalog(String userId) {
        return Collections.singletonList(
                new CatalogItem("The Night Manager", "The story of a night manager", 4)
        );
    }

}
