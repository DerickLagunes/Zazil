package com.zazilweb.model;

import java.io.Serializable;

public class Estado implements Serializable {
    private int id;
    private String nombre;
    private Pais pais;

    public Estado(){}

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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
