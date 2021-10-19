package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.collections.Recurso;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseDisponibilidadRecurso implements DisponibilidadRecurso{
    private final RepositorioRecurso repositorio;
    @Autowired
    public UseCaseDisponibilidadRecurso(RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);
        return recursoMono.map(recurso -> recurso.isPrestado() == true ?
                "Recurso no disponible, prestado el día: "+ recurso.getFechaPrestamo() : "Recurso disponible" );
    }
}
