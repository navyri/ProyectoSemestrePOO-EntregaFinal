import org.example.Models.Usuario;
import org.example.Models.AdministradorUsuario;
import org.springframework.stereotype.Service;

@Service
public class AdministradorUsuarioService {

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
}