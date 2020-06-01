package com.mycompany.springbootelasticsearch.rest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchMovieResponse {

    private List<Hit> hits = new ArrayList<>();
    private String took;

    @Data
    public static class Hit {
        private String index;
        private String id;
        private Float score;
        private String source;
    }

}
