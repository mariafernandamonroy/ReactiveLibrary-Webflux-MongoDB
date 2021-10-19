package com.sofkau.libraryReactive2.usecase;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DisponibilidadRecurso {
    public Mono<String> apply(String titulo);
}
