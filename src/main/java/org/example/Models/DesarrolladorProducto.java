package org.example.Models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "desarrollador_producto")
@DiscriminatorValue("desarrollador_producto")
public class DesarrolladorProducto extends Usuario {

    // ATRIBUTOS
    private String especialidad;

    // CONSTRUCTOR
    public DesarrolladorProducto(UUID id, String nombre, String email, String passwordHash, String rol, java.util.Date fechaRegistro, boolean estadoCuenta, String especialidad) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.especialidad = especialidad;
    }

    public DesarrolladorProducto() {
        super();
    }

    // GETTERS & SETTERS
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
