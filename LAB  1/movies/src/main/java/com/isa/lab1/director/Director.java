package com.isa.lab1.director;

public class Director {

    private String name;
    private int age;
    private String nationality;

    public Director() {}
    
    public Director(String name, int age, String nationality){
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    public String getname(){
        return name;
    }

    public int getage(){
        return age;
    }

    public String getnationality(){
        return nationality;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setage(int age){
        this.age = age;
    }

    public void setnationality(String nationality){
        this.nationality = nationality;
    }

    @Override
    public String toString(){
        return "Director { " + "name: " + name + ", age: " + age + ", nationality: " + nationality + " }";
    }

}
