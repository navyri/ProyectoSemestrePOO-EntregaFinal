package org.example.Models;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "administradores_usuario")
@DiscriminatorValue("administrador_usuario")
public class AdministradorUsuario extends Usuario {

    private boolean nivelAcceso;

    @ManyToMany
    private List<ConsejoSombrio> consejos;

    public AdministradorUsuario() {
        super();
    }

    public AdministradorUsuario(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta, boolean nivelAcceso) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.nivelAcceso = nivelAcceso;
    }

    public boolean getNivelAcceso() {
        return this.nivelAcceso;
    }

    public void setNivelAcceso(boolean nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public List<ConsejoSombrio> getConsejos() {
        return consejos;
    }

    public void setConsejos(List<ConsejoSombrio> consejos) {
        this.consejos = consejos;
    }
}
