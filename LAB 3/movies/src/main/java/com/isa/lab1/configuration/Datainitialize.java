package com.isa.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorService;
import com.isa.lab1.movie.Movie;
import com.isa.lab1.movie.MovieService;

@Component
public class Datainitialize {

    private final MovieService movieservice;
    private final DirectorService directorservice;

    @Autowired
    public Datainitialize(MovieService movieservice, DirectorService directorservice) {
        this.movieservice = movieservice;
        this.directorservice = directorservice;
    }

    @PostConstruct
    public synchronized void Initialize(){

        Director director = Director.builder()
            .id(1L)
            .build();

        Director director_2 = Director.builder()
            .id(2L)
            .build();

        directorservice.create(director);
        directorservice.create(director_2);

        Movie movie = Movie.builder()
            .director(director)
            .title("Saving Private Ryan")
            .length(162)
            .build();

        Movie movie_2 = Movie.builder()
            .director(director)
            .title("War of the Worlds")
            .length(116)
            .build();

        Movie movie_3 = Movie.builder()
            .director(director_2)
            .title("Pulp fiction")
            .length(154)
            .build();

        Movie movie_4 = Movie.builder()
            .director(director_2)
            .title("Inglourious Basterds")
            .length(153)
            .build();

        movieservice.create(movie);
        movieservice.create(movie_2);
        movieservice.create(movie_3);
        movieservice.create(movie_4);

        movieservice.findAll().forEach(System.out::println);
    }
    
}
