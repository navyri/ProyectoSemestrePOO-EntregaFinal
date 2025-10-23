package org.example.Repositories;

import org.example.Models.DesarrolladorProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DesarrolladorProductoRepository extends JpaRepository<DesarrolladorProducto, UUID> {
}
