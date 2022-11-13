package com.isa.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorService;
import com.isa.lab1.movie.Movie;
import com.isa.lab1.movie.MovieService;

@Component
public class Datainitialize {

    private final MovieService movieservice; //final?
    private final DirectorService directorservice;

    @Autowired
    public Datainitialize(MovieService movieservice, DirectorService directorservice) {
        this.movieservice = movieservice;
        this.directorservice = directorservice;
    }

    public void Initialize(){
        Director director = new Director("Steven Spielberg",76, "USA");
        directorservice.create(director);
        Movie movie= new Movie(director, "Saving Private Ryan", 162);
        movieservice.create(movie);
        movie = new Movie(director, "War of the Worlds", 116);
        movieservice.create(movie);

        director = new Director("Quentin Tarantino", 59, "USA");
        directorservice.create(director);
        movie = new Movie(director, "Pulp fiction", 154);
        movieservice.create(movie);
        movie = new Movie(director, "Inglourious Basterds", 153);
        movieservice.create(movie);

        movieservice.findAll().forEach(System.out::println);
    }
    
}
