package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "duenia")
public class Duenia {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String claveMaestra;
    private Date fechaCoronacion;

    // CONSTRUCTOR
    public Duenia(String claveMaestra, Date fechaCoronacion) {
        this.claveMaestra = claveMaestra;
        this.fechaCoronacion = fechaCoronacion;
    }

    public Duenia() {
    }

    // GETTERS & SETTERS
    public String getClaveMaestra() {
        return claveMaestra;
    }

    public void setClaveMaestra(String claveMaestra) {
        this.claveMaestra = claveMaestra;
    }

    public Date getFechaCoronacion() {
        return fechaCoronacion;
    }

    public void setFechaCoronacion(Date fechaCoronacion) {
        this.fechaCoronacion = fechaCoronacion;
    }
}
