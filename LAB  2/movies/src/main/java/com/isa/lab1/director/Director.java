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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private int age;

    private String nationality;

    public Long getid(){
        return id;
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

    public void setid(Long id){
        this.id = id;
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
        return "Director { " + "id: "+ id +", name: " + name + ", age: " + age + ", nationality: " + nationality + " }";
    }

}
