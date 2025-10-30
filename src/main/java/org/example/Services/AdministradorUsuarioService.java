package org.example.Services;

import org.example.Models.Usuario;
import org.example.Models.AdministradorUsuario;
import org.example.Repositories.AdministradorUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class AdministradorUsuarioService {

    @Autowired
    private AdministradorUsuarioRepository administradorUsuarioRepository;

    public List<AdministradorUsuario> getAllAdministradoresUsuario() {
        return administradorUsuarioRepository.findAll();
    }

    public static void suspenderUsuario(List<Usuario> usuarios, Scanner scanner, Usuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario adminUser)) {
            System.out.println("Acceso denegado. Solo administradores usuario pueden suspender cuentas.");
            return;
        }
        if (adminUser.getNivelAcceso() != 1 && adminUser.getNivelAcceso() != 3) {
            System.out.println("Este administrador no tiene permiso para suspender usuarios.");
            return;
        }
        System.out.print("Ingrese el correo del usuario a suspender: ");
        String correoSuspender = scanner.nextLine().trim();
        Usuario usuarioSuspender = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(correoSuspender))
                .findFirst().orElse(null);
        if (usuarioSuspender == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (!usuarioSuspender.isEstadoCuenta()) {
            System.out.println("El usuario ya esta suspendido.");
        } else {
            usuarioSuspender.setEstadoCuenta(false);
            System.out.println("El usuario ha sido suspendido correctamente.");
        }
    }

    public static void reactivarUsuario(List<Usuario> usuarios, Scanner scanner, Usuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario adminUser)) {
            System.out.println("Acceso denegado. Solo administradores usuario pueden reactivar cuentas.");
            return;
        }
        if (adminUser.getNivelAcceso() != 2 && adminUser.getNivelAcceso() != 3) {
            System.out.println("Este administrador no tiene permiso para reactivar usuarios.");
            return;
        }
        System.out.print("Ingrese el correo del usuario a reactivar: ");
        String correoReactivar = scanner.nextLine().trim();
        Usuario usuarioReactivar = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(correoReactivar))
                .findFirst().orElse(null);
        if (usuarioReactivar == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (usuarioReactivar.isEstadoCuenta()) {
            System.out.println("El usuario ya esta activo.");
        } else {
            usuarioReactivar.setEstadoCuenta(true);
            System.out.println("El usuario ha sido reactivado correctamente.");
        }
    }

    public void setNivelAcceso(AdministradorUsuario admin, int nivelAcceso) {
        if(nivelAcceso < 1 || nivelAcceso > 3){
            System.out.println("Nivel de acceso invalido. Debe estar entre 1 y 3.");
            // 1 = Puede suspender usuario
            // 2 = Puede reactivar usuario
            // 3 = Puede suspender y reactivar usuarios
            return;
        }
        admin.setNivelAcceso(nivelAcceso);
    }

    public AdministradorUsuario crearAdministrador(AdministradorUsuario admin) {
        return administradorUsuarioRepository.save(admin);
    }

    public AdministradorUsuario actualizarAdministrador(AdministradorUsuario datosActualizados) {
        datosActualizados.setNivelAcceso(datosActualizados.getNivelAcceso());
        return administradorUsuarioRepository.save(datosActualizados);
    }

    public void eliminarAdministrador(AdministradorUsuario admin){
        administradorUsuarioRepository.delete(admin);
    }
}