package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class Usuario {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected UUID id;
    protected String nombre;
    protected String email;
    protected String passwordHash;
    protected String rol;
    protected Date fechaRegistro;
    protected boolean estadoCuenta;

    // CONSTRUCTOR
    public Usuario(UUID id, String nombre, String email, String passwordHash, String rol, java.util.Date fechaRegistro, boolean estadoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.estadoCuenta = estadoCuenta;
    }

    protected Usuario() {
    }

    // GETTERS & SETTERS
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEstadoCuenta() {
        return this.estadoCuenta;
    }

    public void setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
}
