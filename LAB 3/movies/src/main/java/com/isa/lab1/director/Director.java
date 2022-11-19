package com.isa.lab1.director;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import javax.persistence.OneToMany;
import java.util.List;
import com.isa.lab1.movie.Movie;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "directors")
public class Director implements Serializable{

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "director")
    @ToString.Exclude
    private List<Movie> movies;

}
