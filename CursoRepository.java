ackage org.serratec.coldmart.repository;

import org.serratec.coldmart.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
    List<Curso> findByTituloContainingIgnoreCase(String titulo);
    List<Curso> findByCategoriaId(UUID categoriaId);

    boolean existsByTituloContainingIgnoreCase(String termo);
    boolean existsByTituloAndCategoriaId(String titulo, UUID categoriaId);
}
