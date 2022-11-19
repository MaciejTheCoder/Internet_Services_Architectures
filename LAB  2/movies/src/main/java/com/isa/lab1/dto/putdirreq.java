package com.isa.lab1.otd;

import com.isa.lab1.director.Director;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class putdirreq {

    private String name;

    private int age;

    private String nationality;

    public static BiFunction<Director, putdirreq, Director> dtoToEntityUpdater() {
        return (director, request) -> {
            director.setname(request.getName());
            director.setage(request.getAge());
            director.setnationality(request.getNationality());
            return director;
        };
    }
}
