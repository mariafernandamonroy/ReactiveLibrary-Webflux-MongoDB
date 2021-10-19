package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class UseCaseObtenerTodos implements Supplier<Flux<RecursoDTO>> {
    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapperUtils;
    @Autowired
    public UseCaseObtenerTodos(RecursoMapper mapperUtils, RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repositorio.findAll().map(mapperUtils.mapDatoToDTO());
    }
}
