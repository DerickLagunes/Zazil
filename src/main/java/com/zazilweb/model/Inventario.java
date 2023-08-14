package com.zazilweb.model;

public class Inventario {
    private int id;
    private Producto producto;
    private int cantidad;
    private int origen;

    public Inventario(int id, Producto producto, int cantidad, int origen) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.origen = origen;
    }

    public Inventario() {
    }

    // Getters y setters para cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

}