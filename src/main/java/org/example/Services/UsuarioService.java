package org.example.Services;

import org.example.Models.AdministradorContenido;
import org.example.Models.Cliente;
import org.example.Models.Usuario;
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
        System.out.println("\nPara registrarse llene los campos acontinuacion:");
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
        System.out.print("- Rol (Cliente / Administrador): ");
        String rolIngresado = scanner.nextLine();
        if (!(rolIngresado.equalsIgnoreCase("Cliente") || rolIngresado.equalsIgnoreCase("Administrador"))) {
            System.out.println("Rol no valido, debe ser Cliente o Administrador");
            return;
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
        if (rolIngresado.equalsIgnoreCase("Cliente")) {
            nuevoUsuario = new Cliente(idGenerado, nombreIngresado, correoIngresado, contrasenaIngresada, rolIngresado, fechaRegistro, true);
        } else {
            nuevoUsuario = new AdministradorContenido(idGenerado, nombreIngresado, correoIngresado, contrasenaIngresada, rolIngresado, fechaRegistro, true, "total");
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
