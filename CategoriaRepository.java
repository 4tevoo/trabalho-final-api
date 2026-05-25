package org.serratec.coldmart.repository;

import com.residence.ecommerce.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categorias,UUID>{
    List<Categorias> findByFrontEnd(String FrontEnd);
    List<Categorias> findByBackEnd(String BackEnd);
    List<Categorias> findByMobile(String Mobile);
    List<Categorias> findByFundametos(String Fundamentos);

}
