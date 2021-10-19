package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UseCaseRecursosRecomendados implements RecursosRecomendados {
    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapperUtils;
    @Autowired
    public UseCaseRecursosRecomendados(RecursoMapper mapperUtils, RepositorioRecurso repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get(String clasificacion, String area) {
        Flux<RecursoDTO> listaRecursos = repositorio.findAll().map(mapperUtils.mapDatoToDTO());
        if (!clasificacion.equalsIgnoreCase("none") && area.equalsIgnoreCase("none")){
            return listaRecursos.filter(recurso -> recurso.getClasificacion().equalsIgnoreCase(clasificacion));
        }if (clasificacion.equalsIgnoreCase("none") && !area.equalsIgnoreCase("none")){
            return listaRecursos.filter(recurso -> recurso.getArea().equalsIgnoreCase(area));
        }if (!clasificacion.equalsIgnoreCase("none") && !area.equalsIgnoreCase("none")){
            return listaRecursos.filter(recurso -> recurso.getArea().equalsIgnoreCase(area))
                    .filter(recurso -> recurso.getClasificacion().equalsIgnoreCase(clasificacion));
        }
        return listaRecursos;
    }
}
