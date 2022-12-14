package com.isa.lab1.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;
import com.isa.lab1.dto.getmovieresp;
import com.isa.lab1.dto.getmoviesresp;
import com.isa.lab1.dto.postmoviereq;
import com.isa.lab1.dto.putmoviereq;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    private MovieService movieservice;

    @Autowired
    public MovieController(MovieService movieservice) {
        this.movieservice = movieservice;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<getmoviesresp> getmovies() {
        return ResponseEntity.ok(getmoviesresp.entityToDtoMapper().apply(movieservice.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<getmovieresp> getmovie(@PathVariable("id") Long id) {
        return  movieservice.find(id)
                .map(value -> ResponseEntity.ok(getmovieresp.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postmovie(@RequestBody postmoviereq request, UriComponentsBuilder builder) {
        Movie movie = postmoviereq
                .dtoToEntityMapper(() -> null)
                .apply(request);
                movie = movieservice.create(movie);
        return ResponseEntity.created(builder.pathSegment("api", "movies", "{id}")
                .buildAndExpand(movie.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletemovie(@PathVariable("id") long id) {
        Optional<Movie> movie = movieservice.find(id);
        if (movie.isPresent()) {
            movieservice.delete(movie.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putmovie(@RequestBody putmoviereq request, @PathVariable("id") long id) {
        Optional<Movie> movie = movieservice.find(id);
        if (movie.isPresent()) {
            putmoviereq.dtoToEntityUpdater().apply(movie.get(), request);
            movieservice.update(movie.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
