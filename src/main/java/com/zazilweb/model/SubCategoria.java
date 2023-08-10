package com.zazilweb.model;

import java.io.Serializable;

public class SubCategoria implements Serializable {

    private int id;
    private String nombre;
    private Categoria categoria;
    private boolean status;

    public SubCategoria(){}

    public SubCategoria(int id){
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
