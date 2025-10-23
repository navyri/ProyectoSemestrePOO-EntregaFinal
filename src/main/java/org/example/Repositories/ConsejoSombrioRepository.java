package org.example.Repositories;

import org.example.Models.ConsejoSombrio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ConsejoSombrioRepository extends JpaRepository<ConsejoSombrio, UUID> {
}
