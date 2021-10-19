package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.collections.Recurso;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseDevolverRecurso implements DisponibilidadRecurso{
    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapperUtils;
    private final UseCaseModificarRecurso useCaseModificarRecurso;
    @Autowired
    public UseCaseDevolverRecurso(RecursoMapper mapperUtils, RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
        this.useCaseModificarRecurso = new UseCaseModificarRecurso(mapperUtils,repositorio);
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);
        return recursoMono.flatMap(recurso -> {
            if(recurso.isPrestado()){
                recurso.setPrestado(!recurso.isPrestado());
                return repositorio.save(recurso).thenReturn("Recurso devuelto satisfactoriamente");
            }
            return Mono.just("El recurso no se puede devolver porque no estaba prestado");
        });
    }
}
