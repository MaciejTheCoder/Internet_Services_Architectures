package com.isa.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorService;

@Component
public class Datainitialize {

    private final DirectorService directorservice;

    @Autowired
    public Datainitialize (DirectorService directorservice) {
        this.directorservice = directorservice;
    }

    @PostConstruct
    public synchronized void Initialize(){

        Director director = Director.builder()
            .id(1L)
            .name("Steven Spielberg")
            .age(76)
            .nationality("USA")
            .build();

        Director director_2 = Director.builder()
            .id(2L)
            .name("Quentin Tarantino")
            .age(59)
            .nationality("USA")
            .build();

        directorservice.create(director);
        directorservice.create(director_2);
    }
    
}
