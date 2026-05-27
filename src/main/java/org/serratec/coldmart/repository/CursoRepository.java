package org.serratec.coldmart.repository;

import org.serratec.coldmart.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
    boolean existsByTituloAndCategoriaId(String titulo, UUID categoriaId);
}
