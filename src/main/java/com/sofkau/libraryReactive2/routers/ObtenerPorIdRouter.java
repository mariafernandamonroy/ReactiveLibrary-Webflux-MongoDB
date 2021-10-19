package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.usecase.UseCaseObtenerPorId;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class ObtenerPorIdRouter {
    @Bean
    public RouterFunction<ServerResponse> obtenerPorId(UseCaseObtenerPorId useCaseObtenerPorId) {
        return route(
                GET("/library/obtenerPorId/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseObtenerPorId.get(request.pathVariable("id")), RecursoDTO.class))
        );
    }
}
