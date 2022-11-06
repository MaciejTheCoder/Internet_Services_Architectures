package com.isa.lab1.movie;

import com.isa.lab1.director.Director;

public class Movie {

    private Director director;
    private String title;
    private int length;
    
    public Movie() {}

    public Movie(Director director, String title, int length){
        this.director = director;
        this.title = title;
        this.length = length;
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
        return "Movie { " + "title: " 
        + title + ", " + director + ", length: " + length +" }";
    }

}   
