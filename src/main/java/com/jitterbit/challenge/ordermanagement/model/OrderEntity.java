package com.jitterbit.challenge.ordermanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_order") // "order" é palavra reservada no SQL, usamos um prefixo
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "creationDate")
    private OffsetDateTime creationDate;

    // Relacionamento 1 para Muitos com exclusão em cascata (se deletar o pedido, deleta os itens)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items = new ArrayList<>();

    // Método utilitário para garantir o vínculo bidirecional correto
    public void addItems(List<ItemEntity> newItems) {
        if (newItems != null) {
            newItems.forEach(item -> item.setOrder(this));
            this.items.addAll(newItems);
        }
    }
}