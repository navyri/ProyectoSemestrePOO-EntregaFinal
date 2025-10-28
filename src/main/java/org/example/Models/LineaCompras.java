package org.example.Models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "linea_compras")
public class LineaCompras {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int cantidad;
    private double precioUnit;
    private double subtotal;

    // CONSTRUCTOR
    public LineaCompras(int cantidad, double precioUnit, double subtotal) {
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.subtotal = subtotal;
    }

    public LineaCompras() {

    }

    // GETTERS & SETTERS
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
