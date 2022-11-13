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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.isa.lab1.director.DirectorService;
import com.isa.lab1.otd.getmovieresp;
import com.isa.lab1.otd.getmoviesresp;
import com.isa.lab1.otd.postmoviereq;
import com.isa.lab1.otd.putmoviereq;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    private MovieService movieservice;
    private DirectorService directorservice;

    @Autowired
    public MovieController(MovieService movieservice, DirectorService directorservice) {
        this.movieservice = movieservice;
        this.directorservice = directorservice;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<getmoviesresp> getmovies() {
        List<Movie> all = movieservice.findAll();
        Function<Collection<Movie>, getmoviesresp> mapper = getmoviesresp.entityToDtoMapper();
        getmoviesresp response = mapper.apply(all);
        return ResponseEntity.ok(response);
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
                .dtoToEntityMapper(id -> directorservice.find(id).orElseThrow())
                .apply(request);
                movie = movieservice.create(movie);
        return ResponseEntity.created(builder.pathSegment("api", "movies", "{id}")
                .buildAndExpand(movie.getid()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletemovie(@PathVariable("id") long id) {
        Optional<Movie> movie = movieservice.find(id);
        if (movie.isPresent()) {
            movieservice.delete(movie.get().getid());
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
