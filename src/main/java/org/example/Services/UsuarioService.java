package org.example.Services;

import org.example.Models.*;
import org.example.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuario() {
        return usuarioRepository.findAll();
    }

    public static void registrarUsuario(List<Usuario> usuarios, Scanner scanner) {
        System.out.println("\nPara registrarse llene los campos a continuacion:");
        System.out.print("- Nombre: ");
        String nombreIngresado = scanner.nextLine();
        System.out.print("- Correo electronico: ");
        String correoIngresado = scanner.nextLine();

        for (Usuario usuarioExistente : usuarios) {
            if (usuarioExistente.getEmail().equalsIgnoreCase(correoIngresado)) {
                System.out.println("Ya existe un usuario registrado con ese correo electronico");
                return;
            }
        }

        System.out.print("- Contraseña: ");
        String contrasenaIngresada = scanner.nextLine();

        // Restriccion en base a los roles validos
        String rolIngresado;
        String rolSinEspacios;
        while (true) {
            System.out.print("- Rol (Cliente / Administrador Contenido / Administrador Usuario / Dueña): ");
            rolIngresado = scanner.nextLine().trim();
            rolSinEspacios = rolIngresado.replaceAll("\\s", "").toLowerCase();
            if (rolSinEspacios.equals("cliente")
                    || rolSinEspacios.equals("administradorcontenido")
                    || rolSinEspacios.equals("administradorusuario")
                    || rolSinEspacios.equals("dueña")
                    || rolSinEspacios.equals("duenia")) {
                break;
            }
            System.out.println("Rol no valido, debe ser Cliente, Administrador Contenido, Administrador Usuario o Dueña. Por favor, intentelo de nuevo.");
        }

        if (rolSinEspacios.equals("duenia") || rolSinEspacios.equals("dueña")) {
            for (Usuario usuarioExistente : usuarios) {
                String rolComparar = usuarioExistente.getRol().replaceAll("\\s", "").toLowerCase();
                if (rolComparar.equals("duenia") || rolComparar.equals("dueña")) {
                    System.out.println("Ya existe una Dueña registrada. Solo puede haber una en el sistema.");
                    return;
                }
            }
        }

        System.out.println("\nResumen de datos ingresados:");
        System.out.println("- Nombre ingresado: " + nombreIngresado);
        System.out.println("- Correo electronico ingresado: " + correoIngresado);
        System.out.println("- Rol elegido: " + rolIngresado);
        System.out.print("¿Desea registrar el usuario con estos datos? (Si/No): ");
        String confirmacion = scanner.nextLine();
        if (!confirmacion.equalsIgnoreCase("Si")) {
            System.out.println("Registro cancelado");
            return;
        }

        Usuario nuevoUsuario = null;
        UUID idGenerado = UUID.randomUUID();
        Date fechaRegistro = new Date();

        // Logica en base al rol asignado
        switch (rolSinEspacios) {
            case "cliente" ->
                    nuevoUsuario = new Cliente(idGenerado, nombreIngresado, correoIngresado, contrasenaIngresada, rolIngresado, fechaRegistro, true);

            case "administradorcontenido" -> {
                // Asignar permisos de edicion
                boolean permisosEdicion = true;
                nuevoUsuario = new AdministradorContenido(idGenerado, nombreIngresado, correoIngresado, contrasenaIngresada, rolIngresado, fechaRegistro, true, permisosEdicion);
            }

            case "administradorusuario" -> {
                Random random = new Random();
                int nivelAcceso = random.nextInt(3) + 1;
                AdministradorUsuario adminUsuario = new AdministradorUsuario(idGenerado, nombreIngresado, correoIngresado, contrasenaIngresada, rolIngresado, fechaRegistro, true, 1); // Asignas temporal, luego setNivelAcceso
                AdministradorUsuarioService service = new AdministradorUsuarioService();
                service.setNivelAcceso(adminUsuario, nivelAcceso);
                nuevoUsuario = adminUsuario;
                System.out.println("Nivel de acceso asignado al administrador: " + nivelAcceso + "\n(1: suspender usuario, 2: reactivar usuario, 3: ambas)");
            }
            case "duenia", "dueña" -> {
                System.out.print("- Clave maestra: ");
                String claveMaestra = scanner.nextLine();
                Date fechaCoronacion = new Date(); // O solicita si se requiere
                nuevoUsuario = new Duenia(idGenerado, nombreIngresado, correoIngresado, contrasenaIngresada, "Dueña", fechaRegistro, true, claveMaestra, fechaCoronacion);
            }
        }
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado correctamente");
    }

    public static Usuario iniciarSesion(List<Usuario> usuarios, Scanner scanner) {
        while (true) {
            System.out.println("\nPor favor ingrese sus credenciales:");
            System.out.print("- Correo: ");
            String correo = scanner.nextLine();
            System.out.print("- Contraseña: ");
            String contrasena = scanner.nextLine();

            for (Usuario usuario : usuarios) {
                if (
                        usuario.getEmail().equalsIgnoreCase(correo) &&
                                usuario.getPasswordHash().equals(contrasena) &&
                                usuario.isEstadoCuenta()
                ) {
                    System.out.println("Inicio de sesion exitoso");
                    return usuario;
                }
            }
            System.out.println("Credenciales incorrectas o cuenta inactiva");
            System.out.print("¿Desea intentar nuevamente? (Si/No): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("Si")) {
                return null;
            }
        }
    }

    public static Usuario cerrarSesion(Usuario usuarioActivo) {
        if (usuarioActivo == null) {
            System.out.println("No hay usuario autenticado actualmente");
            return null;
        }
        System.out.println("Su sesion ha sido cerrada exitosamente");
        return null;
    }
}
