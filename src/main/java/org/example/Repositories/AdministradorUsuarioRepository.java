package org.example.Repositories;

import org.example.Models.AdministradorUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AdministradorUsuarioRepository extends JpaRepository<AdministradorUsuario, UUID> {
}
