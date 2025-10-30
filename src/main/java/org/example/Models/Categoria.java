package org.example.Models;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nombre;
    private String descripcion;
    private List<Producto> producto;

    // CONSTRUCTOR
    public Categoria(UUID id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria() {

    }

    // GETTERS & SETTERS
    public List<Producto> getProducto() {
        return producto;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public List<Producto> getProductos() {
        return producto;
    }

    public void setProductos(List<Producto> productos) {
        this.producto = productos;
    }

}
