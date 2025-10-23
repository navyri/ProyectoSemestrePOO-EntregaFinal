package org.example.Repositories;

import org.example.Models.AdministradorContenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AdministradorContenidoRepository extends JpaRepository<AdministradorContenido, UUID> {
}
