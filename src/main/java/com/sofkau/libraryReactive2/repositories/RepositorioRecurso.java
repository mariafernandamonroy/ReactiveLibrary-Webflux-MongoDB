package com.sofkau.libraryReactive2.repositories;

import com.sofkau.libraryReactive2.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
}

