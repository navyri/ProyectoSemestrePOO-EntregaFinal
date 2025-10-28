package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "duenia")
public class Duenia extends Usuario {

    // ATRIBUTOS
    private String claveMaestra;
    private Date fechaCoronacion;

    @OneToMany(mappedBy = "duenia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroEsclavos> registros;

    // CONSTRUCTOR
    public Duenia(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta, String claveMaestra, Date fechaCoronacion) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.claveMaestra = claveMaestra;
        this.fechaCoronacion = fechaCoronacion;
    }

    public Duenia() {
        super();
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

    public List<RegistroEsclavos> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroEsclavos> registros) {
        this.registros = registros;
    }
}
