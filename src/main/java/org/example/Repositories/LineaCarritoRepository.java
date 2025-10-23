package org.example.Repositories;

import org.example.Models.LineaCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaCarritoRepository extends JpaRepository<LineaCarrito, Long> {
}
