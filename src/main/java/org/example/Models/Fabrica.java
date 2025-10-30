package org.example.Models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fabrica")
public class Fabrica {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String pais;
    private String cuidad;
    private int capacidad;
    
    private List<TrabajadorEsclavisado> trabajadorEsclavisadoList;

    private Duenia duenia;

    // CONSTRUCTOR
    public Fabrica(UUID id, String pais, String cuidad, int capacidad) {
        this.id = id;
        this.pais = pais;
        this.cuidad = cuidad;
        this.capacidad = capacidad;
        this.trabajadorEsclavisadoList = new ArrayList<>();
    }

    public Fabrica() {

    }

    // GETTERS & SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<TrabajadorEsclavisado> getTrabajadores() {
        return trabajadorEsclavisadoList;
    }

    public void setTrabajadorEsclavizado(List<TrabajadorEsclavisado> trabajadorEsclavisadoList) {
        this.trabajadorEsclavisadoList=trabajadorEsclavisadoList;
    }

    public Duenia getDuenia() {
        return duenia;
    }

    public void setDuenia(Duenia duenia) {
        this.duenia = duenia;
    }
}
