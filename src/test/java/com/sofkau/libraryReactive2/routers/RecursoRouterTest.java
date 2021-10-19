package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.collections.Recurso;
import com.sofkau.libraryReactive2.dto.RecursoDTO;
import com.sofkau.libraryReactive2.dto.RecursoMapper;
import com.sofkau.libraryReactive2.repositories.RepositorioRecurso;
import com.sofkau.libraryReactive2.usecase.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObtenerTodosRouter.class, UseCaseObtenerTodos.class,
        ObtenerPorIdRouter.class, UseCaseObtenerPorId.class,
        AgregarUnRecursoRouter.class, UseCaseAgregarUnRecurso.class,
        ModificarUnRecursoRouter.class, UseCaseModificarRecurso.class,
        BorrarUnRecursoRouter.class, UseCaseBorrarUnRecurso.class,
        DisponibilidadRecursoRouter.class, UseCaseDisponibilidadRecurso.class,
        DevolverRecursoRouter.class, UseCaseDevolverRecurso.class,
        PrestarRecursoRouter.class, UseCasePrestarRecurso.class,
        RecursosRecomendadosRouter.class, UseCaseRecursosRecomendados.class,
        RecursoMapper.class})

class RecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void obtenerTodos() {
        var dato1 = new Recurso();
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(null);

        var dato2 = new Recurso();
        dato2.setId("2");
        dato2.setTitulo("astronomia");
        dato2.setClasificacion("libro");
        dato2.setArea("ciencia");
        dato2.setPrestado(false);
        dato2.setFechaPrestamo(null);

        when(repositorio.findAll()).thenReturn( Flux.just(dato1,dato2));

        webTestClient.get()
                .uri("/library/obtenerTodos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse.get(0).getTitulo()).isEqualTo(dato1.getTitulo());
                    Assertions.assertThat(userResponse.get(0).getClasificacion()).isEqualTo(dato1.getClasificacion());
                    Assertions.assertThat(userResponse.get(0).getArea()).isEqualTo(dato1.getArea());
                    Assertions.assertThat(userResponse.get(0).isPrestado()).isEqualTo(dato1.isPrestado());
                    Assertions.assertThat(userResponse.get(1).getTitulo()).isEqualTo(dato2.getTitulo());
                    Assertions.assertThat(userResponse.get(1).getClasificacion()).isEqualTo(dato2.getClasificacion());
                    Assertions.assertThat(userResponse.get(1).getArea()).isEqualTo(dato2.getArea());
                    Assertions.assertThat(userResponse.get(1).isPrestado()).isEqualTo(dato2.isPrestado());
                });
    }

    @Test
    void obtenerPorId() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(null);

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/library/obtenerPorId/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Recurso.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse.getId()).isEqualTo(dato1.getId());
                    Assertions.assertThat(userResponse.getTitulo()).isEqualTo(dato1.getTitulo());
                    Assertions.assertThat(userResponse.getClasificacion()).isEqualTo(dato1.getClasificacion());
                    Assertions.assertThat(userResponse.getArea()).isEqualTo(dato1.getArea());
                    Assertions.assertThat(userResponse.isPrestado()).isEqualTo(dato1.isPrestado());
                });
    }

    @Test
    public void agregarUnRecursoTest  () {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(null);

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/library/agregarRecurso")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Recurso.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse.getId()).isEqualTo(dato1.getId());
                    Assertions.assertThat(userResponse.getTitulo()).isEqualTo(dato1.getTitulo());
                    Assertions.assertThat(userResponse.getClasificacion()).isEqualTo(dato1.getClasificacion());
                    Assertions.assertThat(userResponse.getArea()).isEqualTo(dato1.getArea());
                    Assertions.assertThat(userResponse.isPrestado()).isEqualTo(dato1.isPrestado());
                });
    }

    @Test
    void borrarUnRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");

        when(repositorio.deleteById(dato1.getId())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/library/1")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody().isEmpty();
    }

    @Test
    void disponibilidadRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(null);

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/library/recursoDisponible/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("Recurso disponible");
                });
    }

    @Test
    void noDisponibilidadRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/library/recursoDisponible/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("Recurso no disponible, prestado el día: "+ dato1.getFechaPrestamo() );
                });
    }

    @Test
    public void devolverRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);
        when(repositorio.save(dato1)).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/library/devolverRecurso/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("Recurso devuelto satisfactoriamente");
                });
    }

    @Test
    public void noDevolverRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);
        when(repositorio.save(dato1)).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/library/devolverRecurso/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("El recurso no se puede devolver porque no estaba prestado");
                });
    }

    @Test
    public void recursosRecomendados() {
        var dato1 = new Recurso();
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(null);

        var dato2 = new Recurso();
        dato2.setId("2");
        dato2.setTitulo("astronomia");
        dato2.setClasificacion("libro");
        dato2.setArea("ciencia");
        dato2.setPrestado(false);
        dato2.setFechaPrestamo(null);

        var dato3 = new Recurso();
        dato3.setId("3");
        dato3.setTitulo("desayuno");
        dato3.setClasificacion("revista");
        dato3.setArea("gastronomia");
        dato3.setPrestado(false);
        dato3.setFechaPrestamo(null);

        when(repositorio.findAll()).thenReturn(Flux.just(dato1,dato2,dato3));

        webTestClient.get()
                .uri("/library/recursosRecomendados/libro/ciencia")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse.get(0).getTitulo()).isEqualTo(dato1.getTitulo());
                    Assertions.assertThat(userResponse.get(0).getClasificacion()).isEqualTo(dato1.getClasificacion());
                    Assertions.assertThat(userResponse.get(0).getArea()).isEqualTo(dato1.getArea());
                    Assertions.assertThat(userResponse.get(1).getTitulo()).isEqualTo(dato2.getTitulo());
                    Assertions.assertThat(userResponse.get(1).getClasificacion()).isEqualTo(dato2.getClasificacion());
                    Assertions.assertThat(userResponse.get(1).getArea()).isEqualTo(dato2.getArea());
                });
    }

    @Test
    public void prestarRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(false);
        dato1.setFechaPrestamo(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);
        when(repositorio.save(dato1)).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/library/prestarUnRecurso/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("Recurso prestado satisfactoriamente");
                });
    }

    @Test
    public void noPrestarRecurso() {
        var dato1 = new Recurso();
        dato1.setId("1");
        dato1.setTitulo("arquimedes");
        dato1.setClasificacion("libro");
        dato1.setArea("ciencia");
        dato1.setPrestado(true);
        dato1.setFechaPrestamo(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(dato1.getId(),dato1.getTitulo(),dato1.getClasificacion(), dato1.getArea(), dato1.isPrestado());
        Mono<Recurso> recursoMono = Mono.just(dato1);

        when(repositorio.findById(dato1.getId())).thenReturn(recursoMono);
        when(repositorio.save(dato1)).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/library/prestarUnRecurso/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("Recurso no disponible");
                });
    }
}