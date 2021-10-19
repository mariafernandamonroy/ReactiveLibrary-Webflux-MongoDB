package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.usecase.UseCasePrestarRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PrestarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> prestarRecurso(UseCasePrestarRecurso useCasePrestarRecurso ) {
        return route(PUT("/library/prestarUnRecurso/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCasePrestarRecurso.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }
}
