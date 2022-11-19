package com.isa.lab1.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import com.isa.lab1.dto.postdirreq;

@RestController
@RequestMapping("api/directors")
public class DirController {
    private DirectorService directorservice;

    @Autowired
    public DirController(DirectorService directorservice) {
        this.directorservice = directorservice;
    }

    @PostMapping
    public ResponseEntity<Void> postmovie(@RequestBody postdirreq request, UriComponentsBuilder builder) {
        Director director = postdirreq
                .dtoToEntityMapper()
                .apply(request);
                director = directorservice.create(director);
        return ResponseEntity.created(builder.pathSegment("api", "directors", "{id}")
                .buildAndExpand(director.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedirector(@PathVariable("id") long id) {
        Optional<Director> director = directorservice.find(id);
        if (director.isPresent()) {
            directorservice.delete(director.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
