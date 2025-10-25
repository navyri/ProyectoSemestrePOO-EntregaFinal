package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "registro_esclavos")
public class RegistroEsclavos {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date ultimoAcceso;
    private String nivelCifrado;

    @OneToMany(mappedBy = "registro", cascade = CascadeType.ALL)
    private List<TrabajadorEsclavisado> trabajadorEsclavisadoList;

    @ManyToOne
    @JoinColumn(name="duenia_id")
    private Duenia cabrita;

    // CONSTRUCTOR
    public RegistroEsclavos(Date ultimoAcceso, String nivelCifrado) {
        this.ultimoAcceso = ultimoAcceso;
        this.nivelCifrado = nivelCifrado;
        this.trabajadorEsclavisadoList = new ArrayList<>();
    }

    public RegistroEsclavos() {

    }

    // GETTERS & SETTERS
    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getNivelCifrado() {
        return nivelCifrado;
    }

    public void setNivelCifrado(String nivelCifrado) {
        this.nivelCifrado = nivelCifrado;
    }

    public List<TrabajadorEsclavisado> getTrabajadores() { return trabajadorEsclavisadoList; }
    public void setTrabajadores(List<TrabajadorEsclavisado> trabajadores) { this.trabajadorEsclavisadoList = trabajadorEsclavisadoList; }

    public Duenia getDuenia() { return cabrita; }
    public void setDuenia(Duenia duenia) { this.cabrita = duenia; }
}
