package org.serratec.coldmart.repository;

import org.serratec.coldmart.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);
    List<Cliente> findByCpfAndNomeCompleto(String cpf, String nomeCompleto);
}
