package io.infinity.movieinfoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MdbResponse {
    private int id;
    private String title;
    private String overview;
}
