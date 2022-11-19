package com.isa.lab1.movie;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.isa.lab1.director.Director;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movies")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Movie  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "directors")
    private Director director;

    private String title;

    private int length;

    public Long getid() {
        return id;
    }

    public Director getdirector(){
        return director;
    }
    
    public String gettitle() {
        return title;
    }

    public int getlength(){
        return length;
    }

    public void setid(Long id){
        this.id = id;
    }

    public void setdirector(Director director){
        this.director = director;
    }

    public void settitle(String title){
        this.title = title;
    }

    public void setlength(int length){
        this.length = length;
    }

    @Override
    public String toString(){
        return "Movie { " + "id: " + id +", title: " 
        + title + ", " + director + ", length: " + length +" }";
    }



}   
