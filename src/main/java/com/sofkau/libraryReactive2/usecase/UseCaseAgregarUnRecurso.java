package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseAgregarUnRecurso implements AgregarRecurso {
    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapperUtils;
    //    @Autowired
    public UseCaseAgregarUnRecurso(RecursoMapper mapperUtils, RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repositorio.save(mapperUtils.mapperToDato(recursoDTO.getId()).apply(recursoDTO)).map(mapperUtils.mapDatoToDTO());
    }
}
