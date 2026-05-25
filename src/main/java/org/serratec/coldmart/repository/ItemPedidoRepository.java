package org.serratec.coldmart.repository;

import org.serratec.coldmart.entity.ItemPedido;
import org.serratec.coldmart.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
    List<ItemPedido> findByPedido(Pedido pedido);

    long countByPedidoId(UUID pedidoId);
    List<ItemPedido> findByDescontoNotNull();
}
