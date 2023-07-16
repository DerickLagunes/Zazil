package com.zazilweb.model;

import java.io.Serializable;

public class Rol implements Serializable {
    private int id;
    private String nombre_rol;

    public Rol() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
}
