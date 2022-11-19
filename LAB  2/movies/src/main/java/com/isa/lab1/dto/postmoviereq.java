package com.isa.lab1.dto;

import com.isa.lab1.director.Director;
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
public class postmoviereq {

    private Long director;

    private String title;

    private int length;

    public static Function<postmoviereq, Movie> dtoToEntityMapper(
        Function<Long, Director> DirFunction) {
    return request -> Movie.builder()
            .director(DirFunction.apply(request.getDirector()))
            .title(request.getTitle())
            .length(request.getLength())
            .build();  
    }

}
