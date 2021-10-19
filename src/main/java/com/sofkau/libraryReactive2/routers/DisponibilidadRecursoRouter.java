package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.usecase.UseCaseDisponibilidadRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DisponibilidadRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> disponibilidadRecurso(UseCaseDisponibilidadRecurso useCaseDisponibilidadRecurso) {
        return route(GET("/library/recursoDisponible/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseDisponibilidadRecurso.apply(request.pathVariable("id")),String.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}
