package io.infinity.moviecatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingsData {
    private String userId;
    private List<Rating> ratings;
}
