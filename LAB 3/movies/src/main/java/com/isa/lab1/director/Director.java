package com.isa.lab1.director;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.isa.lab1.movie.Movie;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "directors")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Director implements Serializable{

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "director")
    @ToString.Exclude
    private List<Movie> movies;

}
