package org.example.Models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "compra")
public class Compra {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Date fecha;
    private double total;
    private String estado;

    @OneToMany
    private List<LineaCompras> lineaCompras;

    // CONSTRUCTOR
    public Compra(UUID id, Date fecha, double total, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.lineaCompras = new ArrayList<>();
    }

    public Compra() {

    }

    // GETTERS & SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<LineaCompras> getLineaCompras() {
        return lineaCompras;
    }

    public void setLineaCompras(List<LineaCompras> lineaCompras) {
        this.lineaCompras = lineaCompras;
    }
}