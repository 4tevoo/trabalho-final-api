package org.serratec.coldmart.repository;

import com.residence.ecommerce.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    Cliente findByCpf(String Cpf);
    Cliente findByNomeCompelto(String NomeCompleto);
    List<Cliente> findByCpfAndNomeCompletp(String Cpf, String NomeCompleto);
}
