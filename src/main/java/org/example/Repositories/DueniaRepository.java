package org.example.Repositories;

import org.example.Models.Duenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DueniaRepository extends JpaRepository<Duenia, Long> {
}
