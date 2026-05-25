package org.serratec.coldmart.repository;

import com.residence.ecommerce.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CursoRepository extends JpaRepository<Cursos, UUID> {

    List<Cursos> findByBancoDeDados(String BancoDeDados);
    List<Cursos> findByLogicaDeProgramação(String LogicaDeProgramação);

    boolean existsByReactNotNull();
    boolean existsByFrontEndEssencial(String frontEndEssencial);
}
