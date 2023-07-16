package com.zazilweb.model;

import java.io.Serializable;

public class Operacion implements Serializable {
    private String operacion;
    private String media;
    private String descripcion;

    public Operacion() {
    }

    public Operacion(String operacion, String media, String descripcion) {
        this.operacion = operacion;
        this.media = media;
        this.descripcion = descripcion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
