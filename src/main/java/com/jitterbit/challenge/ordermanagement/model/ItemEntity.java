package com.jitterbit.challenge.ordermanagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    // Adicionamos um ID substituto (Surrogate Key) por boas práticas do JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A coluna no banco se chamará 'orderId' e fará referência à tabela tb_order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    @Column(name = "productId")
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;
}