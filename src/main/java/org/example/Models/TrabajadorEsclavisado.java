package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "trabajador_esclavisado")
public class TrabajadorEsclavisado {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nombre;
    private String paisOrigen;
    private int edad;
    private Date fechaCaptura;
    private int salud;

    @ManyToOne
    @JoinColumn(name="frabrica_id")
    private Fabrica asignadoA;

    // CONSTRUCTOR
    public TrabajadorEsclavisado(UUID id, String nombre, String paisOrigen, int edad, Date fechaCaptura, int salud, Fabrica asignadoA) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.edad = edad;
        this.fechaCaptura = fechaCaptura;
        this.salud = salud;
        this.asignadoA = asignadoA;
    }

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private RegistroEsclavos registro;

    protected TrabajadorEsclavisado() {
    }

    // GETTERS & SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public Fabrica getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(Fabrica asignadoA) {
        this.asignadoA = asignadoA;
    }

    public RegistroEsclavos getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroEsclavos registro) {
        this.registro = registro;
    }
}

