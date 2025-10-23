package org.example.Repositories;

import org.example.Models.LineaCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaComprasRepository extends JpaRepository<LineaCompras, Long> {
}
