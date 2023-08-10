package com.zazilweb.model;

import java.io.Serializable;

public class Categoria implements Serializable {
    private int id;
    private String nombre;
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
