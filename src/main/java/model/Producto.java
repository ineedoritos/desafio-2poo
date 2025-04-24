package model;

public class Producto {
    private Long id;
    private String nombre;
    private double precio;
    private int cantidad;
    private java.sql.Date fechaExpiracion;

    public Producto() {}
    public Producto(Long id, String nombre, double precio, int cantidad, java.sql.Date fechaExpiracion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaExpiracion = fechaExpiracion;
    }

    public Producto(String nombre, double precio, int cantidad, java.sql.Date fechaExpiracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaExpiracion = fechaExpiracion;

    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    public void setFechaExpiracion(java.sql.Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    public java.sql.Date getFechaExpiracion() {
        return this.fechaExpiracion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getPrecio() {
        return this.precio;
    }
}