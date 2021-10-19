package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.collections.Recurso;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class UseCasePrestarRecurso implements DisponibilidadRecurso {
    private final RepositorioRecurso repositorio;
    @Autowired
    public UseCasePrestarRecurso(RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);
        return recursoMono.flatMap(recurso -> {
            if (!recurso.isPrestado()) {
                recurso.setFechaPrestamo(LocalDate.now());
                recurso.setPrestado(true);
                return repositorio.save(recurso)
                        .thenReturn("Recurso prestado satisfactoriamente");
            }
            return Mono.just("Recurso no disponible");
        });
    }
}
