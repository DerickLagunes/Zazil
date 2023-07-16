package com.zazilweb.model;

import java.io.Serializable;

public class SubCategoria implements Serializable {

    private int id;
    private String nombre;
    private Categoria categoria;

    public SubCategoria(){}

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
