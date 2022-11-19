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
public class postdirreq {

    private Long id;

    public static Function<postdirreq, Director> dtoToEntityMapper() {
    return request -> Director.builder()
            .id(request.getId())
            .build();  
    }

}
