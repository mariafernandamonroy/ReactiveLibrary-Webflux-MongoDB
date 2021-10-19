package com.sofkau.libraryReactive2.dto;

import com.sofkau.libraryReactive2.collections.Recurso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Component
public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setTitulo(dto.getTitulo());
        recurso.setClasificacion(dto.getClasificacion());
        recurso.setArea(dto.getArea());
        recurso.setPrestado(dto.isPrestado());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setTitulo(collection.getTitulo());
        recursoDTO.setClasificacion(collection.getClasificacion());
        recursoDTO.setArea(collection.getArea());
        recursoDTO.setPrestado(collection.isPrestado());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection) {
        if (collection == null) {
            return null;
        }
        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso recurso = (Recurso) listTracks.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }

    public Function<RecursoDTO, Recurso> mapperToDato(String id) {
        return updateDato -> {
            var recurso = new Recurso();
            recurso.setId(id);
            recurso.setTitulo(updateDato.getTitulo());
            recurso.setClasificacion(updateDato.getClasificacion());
            recurso.setArea(updateDato.getArea());
            recurso.setPrestado(updateDato.isPrestado());
            recurso.setFechaPrestamo(updateDato.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapDatoToDTO() {
        return entity -> new RecursoDTO(entity.getId(), entity.getTitulo(), entity.getClasificacion(), entity.getArea(), entity.isPrestado());
    }
}
