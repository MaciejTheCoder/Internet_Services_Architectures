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
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class putmoviereq {

    private String title;

    private int length;

    public static BiFunction<Movie, putmoviereq, Movie> dtoToEntityUpdater() {
        return (movie, request) -> {
            movie.settitle(request.getTitle());
            movie.setlength(request.getLength());
            return movie;
        };
    }
}
