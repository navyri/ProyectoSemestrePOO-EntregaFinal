package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Entity
@Table(name = "carrito")
public class Carrito {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Date fechaCreacion;

    @ManyToMany
    private List<Producto> producto;

    @OneToMany
    private List<LineaCarrito> lineaCarritos;

    // CONSTRUCTOR
    public Carrito(UUID id, Date fechaCreacion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.producto = new ArrayList<>();
        this.lineaCarritos = new ArrayList<>();
    }

    public Carrito() {

    }

    // GETTERS & SETTERS
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID newID){
        this.id = newID;
    }

    public Date getFechaCreacion(){
        return this.fechaCreacion;
    }

    public void setfechaCreacion(Date newFecha){
        this.fechaCreacion = newFecha;
    }

}
