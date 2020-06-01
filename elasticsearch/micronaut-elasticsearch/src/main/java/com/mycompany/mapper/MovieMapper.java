package com.mycompany.mapper;

import com.mycompany.model.Movie;
import com.mycompany.rest.dto.CreateMovieRequest;
import com.mycompany.rest.dto.SearchMovieResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.mapstruct.Mapper;

import javax.inject.Singleton;

@Singleton
@Mapper(componentModel = "jsr330")
public interface MovieMapper {

    Movie toMovie(CreateMovieRequest createMovieRequest);

    default SearchMovieResponse toSearchMovieDto(SearchHits searchHits, TimeValue took) {
        SearchMovieResponse searchMovieDto = new SearchMovieResponse();
        for (SearchHit searchHit : searchHits.getHits()) {
            SearchMovieResponse.Hit hit = new SearchMovieResponse.Hit();
            hit.setIndex(searchHit.getIndex());
            hit.setId(searchHit.getId());
            hit.setScore(searchHit.getScore());
            hit.setSource(searchHit.getSourceAsString());
            searchMovieDto.getHits().add(hit);
        }
        searchMovieDto.setTook(took.toString());
        return searchMovieDto;
    }
}
