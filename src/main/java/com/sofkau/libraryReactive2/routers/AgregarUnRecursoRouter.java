package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.usecase.UseCaseAgregarUnRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AgregarUnRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> agregarUnRecurso(UseCaseAgregarUnRecurso useCaseAgregarUnRecurso ) {
        return route(POST("/library/agregarRecurso").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> useCaseAgregarUnRecurso.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}
