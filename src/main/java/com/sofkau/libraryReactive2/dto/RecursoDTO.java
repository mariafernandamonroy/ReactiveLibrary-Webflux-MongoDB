package com.sofkau.libraryReactive2.dto;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class RecursoDTO {
    @Id
    private String id;
    private String titulo;
    private String clasificacion;
    private String area;
    private boolean prestado;
    private LocalDate fechaPrestamo = null;

    public RecursoDTO(){
    }

    public RecursoDTO(String id, String titulo, String clasificacion, String area, boolean prestado) {
        this.id = id;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.area = area;
        this.prestado = prestado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}