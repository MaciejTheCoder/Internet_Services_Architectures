package com.isa.lab1.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.isa.lab1.director.Director;

@Repository
public interface MovieRepo  extends JpaRepository<Movie, Long> {

    Optional<Movie> findByIdAndDirector(Long id, Director director);

    List<Movie> findAllByDirector(Director director);
}
