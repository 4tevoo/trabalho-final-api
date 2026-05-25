package org.serratec.coldmart.repository;

import com.residence.ecommerce.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,UUID>{
 List<Categoria>findByNomeIgnoreCase(String nome);
}
