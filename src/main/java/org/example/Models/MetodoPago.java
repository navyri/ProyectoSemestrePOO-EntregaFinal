package org.example.Models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "metodo_pago")
public class MetodoPago {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String tipo;
    private String titular;
    private String numeroEnmascarado;

    // CONSTRUCTOR
    public MetodoPago(UUID id, String tipo, String titular, String numeroEnmascarado) {
        this.id = id;
        this.tipo = tipo;
        this.titular = titular;
        this.numeroEnmascarado = numeroEnmascarado;
    }

    public MetodoPago() {

    }

    // GETTERS & SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroEnmascarado() {
        return numeroEnmascarado;
    }

    public void setNumeroEnmascarado(String numeroEnmascarado) {
        this.numeroEnmascarado = numeroEnmascarado;
    }
}
