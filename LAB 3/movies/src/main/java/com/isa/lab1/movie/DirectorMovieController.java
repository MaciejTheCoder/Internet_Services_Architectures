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

import com.isa.lab1.director.Director;
import com.isa.lab1.director.DirectorService;
import com.isa.lab1.dto.getmovieresp;
import com.isa.lab1.dto.getmoviesresp;
import com.isa.lab1.dto.postmoviereq;
import com.isa.lab1.dto.putmoviereq;

@RestController
@RequestMapping("api/directors/{id}/movies")
public class DirectorMovieController {
    private MovieService movieservice;
    private DirectorService directorservice;

    @Autowired
    public DirectorMovieController(MovieService movieservice, DirectorService directorservice) {
        this.movieservice = movieservice;
        this.directorservice = directorservice;
    }

    @GetMapping
    public ResponseEntity<getmoviesresp> getmovies(@PathVariable("id") Long id) {
        Optional<Director> director = directorservice.find(id);
        return director.map(value -> ResponseEntity.ok(getmoviesresp.entityToDtoMapper().apply(movieservice.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCharacterResponse> getCharacter(@PathVariable("username") String username,
                                                             @PathVariable("id") long id) {
        return characterService.find(username, id)
                .map(value -> ResponseEntity.ok(GetCharacterResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<getmovieresp> getmovie(@PathVariable("id") Long id, @PathVariable("id") Long id) {
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
