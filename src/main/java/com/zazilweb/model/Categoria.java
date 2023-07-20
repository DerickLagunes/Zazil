package com.zazilweb.model;

import java.io.Serializable;

public class Categoria implements Serializable {
    private int id;
    private String nombre;

    public Categoria(){}

    public Categoria(int id){
        this.id = id;
    }

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
}
