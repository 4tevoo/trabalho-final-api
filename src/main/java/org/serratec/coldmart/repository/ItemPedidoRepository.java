package org.serratec.coldmart.repository;

import com.residence.ecommerce.entity.ItemPedido;
import com.residence.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID>{

    Pedido findById(String Id);
    List<ItemPedido> findByPedido(Pedido pedido);
    long countByPedidoId(Long pedidoId);
    List<ItemPedido> findByDescontoNotNull();

}
