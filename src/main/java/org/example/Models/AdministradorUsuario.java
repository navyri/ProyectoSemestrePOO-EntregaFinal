package org.example.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorValue;
import java.util.UUID;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "administradores_usuario")
@DiscriminatorValue("administrador_usuario")
public class AdministradorUsuario extends Usuario {

    private int nivelAcceso;

    @ManyToMany(mappedBy = "administradoresUsuario")
    private List<ConsejoSombrio> consejos;

    public AdministradorUsuario() {
        super();
    }

    public AdministradorUsuario(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta, int nivelAcceso) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return this.nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public List<ConsejoSombrio> getConsejos() {
        return consejos;
    }

    public void setConsejos(List<ConsejoSombrio> consejos) {
        this.consejos = consejos;
    }
}
