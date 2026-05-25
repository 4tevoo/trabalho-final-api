package org.serratec.coldmart.repository;

import com.residence.ecommerce.entity.Pedido;
import com.residence.ecommerce.entity.StatusPagamento;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoRepository {
    List findByStatusOrderByDataPedidoDesc(StatusPagamento status, Pageable pageable); //Ordenação
    List<Pedido> findByClienteId(Long clienteId);

}
