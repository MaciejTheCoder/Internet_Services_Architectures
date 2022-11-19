package com.isa.lab1.dto;

import com.isa.lab1.movie.Movie;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getmoviesresp {
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class MovieEntry {
        private Long id;
        private String title;
    }
    @Singular
    private List<MovieEntry> movies;

    public static Function<Collection<Movie>, getmoviesresp> entityToDtoMapper() {
        return movies -> {
            getmoviesrespBuilder response = getmoviesresp.builder();
            movies.stream()
                    .map(movie -> MovieEntry.builder()
                            .id(movie.getid())
                            .title(movie.gettitle())
                            .build())
                    .forEach(response::movie);
            return response.build();
        };
    }

}
