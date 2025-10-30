package org.example.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "administradores_contenido")
@DiscriminatorValue("administrador_contenido")
public class AdministradorContenido extends Usuario {

    private boolean permisosEdicion;

    private List<ConsejoSombrio> consejos;

    public AdministradorContenido() {
        super();
    }

    public AdministradorContenido(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta, boolean permisosEdicion) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.permisosEdicion = permisosEdicion;
    }

    public boolean getPermisosEdicion() {
        return permisosEdicion;
    }

    public void setPermisosEdicion(boolean permisosEdicion) {
        this.permisosEdicion = permisosEdicion;
    }

    public List<ConsejoSombrio> getConsejos() {
        return consejos;
    }

    public void setConsejos(List<ConsejoSombrio> consejos) {
        this.consejos = consejos;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "admin_contenido_id")
    private List<Producto> producto;

    public List<Producto> getProductos() {
        return producto;
    }

    public void setProductos(List<Producto> productos) {
        this.producto = productos;
    }
}