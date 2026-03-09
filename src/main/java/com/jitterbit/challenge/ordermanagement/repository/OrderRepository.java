package com.jitterbit.challenge.ordermanagement.repository;

import com.jitterbit.challenge.ordermanagement.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a entidade Order.
 * Importante: O ID foi definido como String para suportar o 'numeroPedido'
 * como chave primária, conforme exigido no desafio.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    // O JpaRepository já provê os métodos save(), findById(), findAll(), deleteById()
}