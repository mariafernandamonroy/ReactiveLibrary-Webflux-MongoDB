package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.collections.Recurso;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UseCaseBorrarUnRecurso implements BorarRecurso {
    private final RepositorioRecurso repositorio;
    @Autowired
    public UseCaseBorrarUnRecurso(RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono get(String id) {
        if (Objects.isNull(id)) {
            return Mono.empty();
        }
        return repositorio.deleteById(id);
    }
}
