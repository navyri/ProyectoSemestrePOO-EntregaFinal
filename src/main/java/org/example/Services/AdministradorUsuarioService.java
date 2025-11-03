package org.example.Services;

import org.example.Models.Duenia;
import org.example.Models.Usuario;
import org.example.Models.AdministradorUsuario;
import org.example.Repositories.AdministradorUsuarioRepository;
import org.example.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service
public class AdministradorUsuarioService {

    @Autowired
    private AdministradorUsuarioRepository administradorUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AdministradorUsuario> getAllAdministradoresUsuario() {
        return administradorUsuarioRepository.findAll();
    }

    public AdministradorUsuario crearAdministrador(AdministradorUsuario admin) {
        return administradorUsuarioRepository.save(admin);
    }

    public AdministradorUsuario actualizarAdministrador(AdministradorUsuario datosActualizados) {
        return administradorUsuarioRepository.save(datosActualizados);
    }

    public void eliminarAdministrador(UUID adminId) {
        administradorUsuarioRepository.deleteById(adminId);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean suspenderUsuario(String email, AdministradorUsuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario)) {
            return false;
        }
        AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
        if (!adminUser.getNivelAcceso()) {
            return false;
        }
        Usuario usuarioSuspendido = buscarUsuarioPorEmail(email);
        if (usuarioSuspendido == null) {
            return false;
        }
        if (usuarioSuspendido instanceof Duenia) {
            return false;
        }
        if (!usuarioSuspendido.isEstadoCuenta()) {
            return false;
        }
        usuarioSuspendido.setEstadoCuenta(false);
        usuarioRepository.save(usuarioSuspendido);
        return true;
    }

    public boolean reactivarUsuario(String email, AdministradorUsuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario)) {
            return false;
        }
        AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
        if (!adminUser.getNivelAcceso()) {
            return false;
        }
        Usuario usuarioReactivar = buscarUsuarioPorEmail(email);
        if (usuarioReactivar == null) {
            return false;
        }
        if (usuarioReactivar.isEstadoCuenta()) {
            return false;
        }
        usuarioReactivar.setEstadoCuenta(true);
        usuarioRepository.save(usuarioReactivar);
        return true;
    }

    public void suspenderUsuario(Scanner scanner, Usuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario)) {
            System.out.println("Acceso denegado. Solo administradores usuario pueden suspender cuentas.");
            return;
        }
        AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
        if (!adminUser.getNivelAcceso()) {
            System.out.println("No tienes permiso para suspender o reactivar usuarios.");
            return;
        }
        System.out.print("Ingrese el correo del usuario a suspender: ");
        String correoSuspender = scanner.nextLine().trim();
        Usuario usuarioSuspendido = buscarUsuarioPorEmail(correoSuspender);
        if (usuarioSuspendido == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (usuarioSuspendido instanceof Duenia) {
            System.out.println("No puedes suspender a la dueña. Permiso denegado.");
            return;
        }
        if (!usuarioSuspendido.isEstadoCuenta()) {
            System.out.println("El usuario ya esta suspendido.");
        } else {
            usuarioSuspendido.setEstadoCuenta(false);
            usuarioRepository.save(usuarioSuspendido);
            System.out.println("El usuario ha sido suspendido correctamente.");
        }
    }
    //Metodo Suspender Usuario Interfaz
    public void suspenderUsuarioInterfaz(Usuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario)) {
            JOptionPane.showMessageDialog(null,"Acceso denegado. Solo administradores usuario pueden suspender cuentas.");
            return;
        }
        AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
        if (!adminUser.getNivelAcceso()) {
            JOptionPane.showMessageDialog(null,"No tienes permiso para suspender o reactivar usuarios.");
            return;
        }
        String correoSuspender = JOptionPane.showInputDialog("Ingrese el correo del usuario a suspender: ").trim();
        Usuario usuarioSuspendido = buscarUsuarioPorEmail(correoSuspender);
        if (usuarioSuspendido == null) {
            JOptionPane.showMessageDialog(null,"Usuario no encontrado.");
            return;
        }
        if (usuarioSuspendido instanceof Duenia) {
            JOptionPane.showMessageDialog(null,"No puedes suspender a la dueña. Permiso denegado.");
            return;
        }
        if (!usuarioSuspendido.isEstadoCuenta()) {
            JOptionPane.showMessageDialog(null,"El usuario ya esta suspendido.");
        } else {
            usuarioSuspendido.setEstadoCuenta(false);
            usuarioRepository.save(usuarioSuspendido);
            JOptionPane.showMessageDialog(null,"El usuario ha sido suspendido correctamente.");
        }
    }

    public void reactivarUsuario(Scanner scanner, Usuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario)) {
            System.out.println("Acceso denegado. Solo administradores usuario pueden reactivar cuentas.");
            return;
        }
        AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
        if (!adminUser.getNivelAcceso()) {
            System.out.println("No tienes permiso para suspender o reactivar usuarios.");
            return;
        }
        System.out.print("Ingrese el correo del usuario a reactivar: ");
        String correoReactivar = scanner.nextLine().trim();
        Usuario usuarioReactivar = buscarUsuarioPorEmail(correoReactivar);
        if (usuarioReactivar == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (usuarioReactivar.isEstadoCuenta()) {
            System.out.println("El usuario ya esta activo.");
        } else {
            usuarioReactivar.setEstadoCuenta(true);
            usuarioRepository.save(usuarioReactivar);
            System.out.println("El usuario ha sido reactivado correctamente.");
        }
    }

    //METODDO REACTIVAR USUARIO INTERFAZ
    public void reactivarUsuarioInterfaz(Usuario usuarioActivo) {
        if (!(usuarioActivo instanceof AdministradorUsuario)) {
            JOptionPane.showMessageDialog(null,"Acceso denegado. Solo administradores usuario pueden reactivar cuentas.");
            return;
        }
        AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
        if (!adminUser.getNivelAcceso()) {
            JOptionPane.showMessageDialog(null,"No tienes permiso para suspender o reactivar usuarios.");
            return;
        }

        String correoReactivar = JOptionPane.showInputDialog("Ingrese el correo del usuario a reactivar: ").trim();
        Usuario usuarioReactivar = buscarUsuarioPorEmail(correoReactivar);
        if (usuarioReactivar == null) {
            JOptionPane.showMessageDialog(null,"Usuario no encontrado.");
            return;
        }
        if (usuarioReactivar.isEstadoCuenta()) {
            JOptionPane.showMessageDialog(null,"El usuario ya esta activo.");
        } else {
            usuarioReactivar.setEstadoCuenta(true);
            usuarioRepository.save(usuarioReactivar);
            JOptionPane.showMessageDialog(null,"El usuario ha sido reactivado correctamente.");
        }
    }
}