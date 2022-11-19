package com.isa.lab1.dto;

import com.isa.lab1.director.Director;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getdirresp {
    
    private Long id;

    private String name;

    private int age;

    private String nationality;

    public static Function<Director, getdirresp> entityToDtoMapper() {
        return director -> getdirresp.builder()
                .id(director.getid())
                .name(director.getname())
                .age(director.getage())
                .nationality(director.getnationality())
                .build();
    }
}
