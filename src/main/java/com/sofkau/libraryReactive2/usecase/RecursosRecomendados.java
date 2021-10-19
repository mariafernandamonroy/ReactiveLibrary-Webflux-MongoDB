package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RecursosRecomendados {
    public Flux<RecursoDTO> get(String clasificacion, String area);
}