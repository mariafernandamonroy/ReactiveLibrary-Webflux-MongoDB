package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AgregarRecurso {
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}

