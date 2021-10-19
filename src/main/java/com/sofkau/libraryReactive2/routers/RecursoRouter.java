package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> modificarUnRecurso(UseCaseModificarRecurso useCaseModificarRecurso ) {
        return route(PUT("/library/modificarRecurso").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> useCaseModificarRecurso.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

//    @Bean
//    public RouterFunction<ServerResponse> recursosRecomendados(UseCaseRecursosRecomendados useCaseRecursosRecomendados) {
//        return route(
//                GET("/library/recursosRecomendados/{clasificacion}/{area}").and(accept(MediaType.APPLICATION_JSON)),
//                request -> ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromPublisher(useCaseRecursosRecomendados.get(request.pathVariable("clasificacion"),request.pathVariable("area")), RecursoDTO.class))
//        );
//    }

}
