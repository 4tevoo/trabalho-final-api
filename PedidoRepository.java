package org.serratec.coldmart.repository;

import org.serratec.coldmart.entity.Pedido;
import org.serratec.coldmart.enums.StatusPagamento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByStatusOrderByDataPedidoDesc(StatusPagamento status, Pageable pageable);

    List<Pedido> findByClienteId(UUID clienteId);
}
