package org.example.Repositories;

import org.example.Models.RegistroEsclavos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroEsclavosRepository extends JpaRepository<RegistroEsclavos, Long> {
}
