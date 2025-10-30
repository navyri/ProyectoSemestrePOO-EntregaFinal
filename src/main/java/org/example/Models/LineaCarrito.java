package org.example.Models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "linea_carrito")
public class LineaCarrito {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int cantidad;
    private double subtotal;

    // CONSTRUCTOR
    public LineaCarrito(int cantidad, double subtotal) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public LineaCarrito() {

    }

    // GETTERS & SETTERS
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
