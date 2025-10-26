package org.example.Services;

import org.example.Models.Usuario;
import org.example.Models.AdministradorUsuario;
import org.example.Repositories.AdministradorUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorUsuarioService {

    @Autowired
    private AdministradorUsuarioRepository administradorUsuarioRepository;

    public List<AdministradorUsuario> getAllAdministradoresUsuario() {
        return administradorUsuarioRepository.findAll();
    }

    public void suspenderUsuario(Usuario usuario) {
        if (!usuario.isEstadoCuenta()) {
            System.out.println("El usuario ya está suspendido");
        } else {
            usuario.setEstadoCuenta(false);
            System.out.println("El usuario ha sido suspendido");
        }
    }

    public void reactivarUsuario(Usuario usuario) {
        if (usuario.isEstadoCuenta()) {
            System.out.println("El usuario ya está activo");
        } else {
            usuario.setEstadoCuenta(true);
            System.out.println("El usuario ha sido reactivado");
        }
    }

    public void setNivelAcceso(AdministradorUsuario admin, int nivelAcceso) {
        if(nivelAcceso < 1 || nivelAcceso > 5){
            System.out.println("Nivel de acceso inválido. Debe estar entre 1 y 5.");
            return;
        }
        admin.setNivelAcceso(nivelAcceso);
    }

    public AdministradorUsuario crearAdministrador(AdministradorUsuario admin) {
        return administradorUsuarioRepository.save(admin);
    }

    // Actualizar
    public AdministradorUsuario actualizarAdministrador(AdministradorUsuario datosActualizados) {
        datosActualizados.setNivelAcceso(datosActualizados.getNivelAcceso());
        return administradorUsuarioRepository.save(datosActualizados);
    }

    public void eliminarAdministrador(AdministradorUsuario admin){
        administradorUsuarioRepository.delete(admin);
    }

}