package com.isa.lab1.dto;

import com.isa.lab1.director.Director;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getdirsresp {
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class DirEntry {
        private Long id;
        private String name;
    }
    @Singular
    private List<DirEntry> directors;

    public static Function<Collection<Director>, getdirsresp> entityToDtoMapper() {
        return directors -> {
            getdirsrespBuilder response = getdirsresp.builder();
            directors.stream()
                    .map(director -> DirEntry.builder()
                            .id(director.getid())
                            .name(director.getname())
                            .build())
                    .forEach(response::director);
            return response.build();
        };
    }

}
