package com.isa.lab1.otd;

import com.isa.lab1.movie.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getmovieresp {
    
    private Long id;

    private String director;

    private String title;

    private int length;

    public static Function<Movie, getmovieresp> entityToDtoMapper() {
        return movie -> getmovieresp.builder()
                .id(movie.getid())
                .director(movie.getdirector().getname())
                .title(movie.gettitle())
                .length(movie.getlength())
                .build();
    }
}
