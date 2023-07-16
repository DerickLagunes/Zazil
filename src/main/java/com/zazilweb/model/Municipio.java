package com.zazilweb.model;

import java.io.Serializable;

public class Municipio implements Serializable {
    private int id;
    private String nombre;
    private Estado estado;

    public Municipio(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
