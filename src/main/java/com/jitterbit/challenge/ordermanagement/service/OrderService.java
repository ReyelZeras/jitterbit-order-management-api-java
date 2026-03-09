package com.jitterbit.challenge.ordermanagement.service;

import com.jitterbit.challenge.ordermanagement.dto.ItemResponseDTO;
import com.jitterbit.challenge.ordermanagement.dto.OrderRequestDTO;
import com.jitterbit.challenge.ordermanagement.dto.OrderResponseDTO;
import com.jitterbit.challenge.ordermanagement.exception.OrderNotFoundException;
import com.jitterbit.challenge.ordermanagement.model.ItemEntity;
import com.jitterbit.challenge.ordermanagement.model.OrderEntity;
import com.jitterbit.challenge.ordermanagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        OrderEntity entity = mapToEntity(request);
        OrderEntity savedEntity = orderRepository.save(entity);
        return mapToResponse(savedEntity);
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(String orderId) {
        OrderEntity entity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado: " + orderId));
        return mapToResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> listAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDTO updateOrder(String orderId, OrderRequestDTO request) {
        OrderEntity existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado: " + orderId));

        // Atualizando os dados base
        existingOrder.setValue(request.valorTotal());
        existingOrder.setCreationDate(request.dataCriacao());

        // Atualizando a lista de itens (limpa os atuais e adiciona os novos para aproveitar o orphanRemoval)
        existingOrder.getItems().clear();

        List<ItemEntity> newItems = request.items().stream()
                .map(itemReq -> {
                    ItemEntity item = new ItemEntity();
                    item.setProductId(itemReq.idItem());
                    item.setQuantity(itemReq.quantidadeItem());
                    item.setPrice(itemReq.valorItem());
                    return item;
                }).collect(Collectors.toList());

        existingOrder.addItems(newItems);

        OrderEntity updatedEntity = orderRepository.save(existingOrder);
        return mapToResponse(updatedEntity);
    }

    @Transactional
    public void deleteOrder(String orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Pedido não encontrado: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    // --- Métodos Privados de Mapeamento (Transformation) ---

    private OrderEntity mapToEntity(OrderRequestDTO request) {
        OrderEntity order = new OrderEntity();
        order.setOrderId(request.numeroPedido());
        order.setValue(request.valorTotal());
        order.setCreationDate(request.dataCriacao());

        if (request.items() != null) {
            List<ItemEntity> items = request.items().stream().map(itemReq -> {
                ItemEntity item = new ItemEntity();
                item.setProductId(itemReq.idItem());
                item.setQuantity(itemReq.quantidadeItem());
                item.setPrice(itemReq.valorItem());
                return item;
            }).collect(Collectors.toList());

            order.addItems(items);
        }
        return order;
    }

    private OrderResponseDTO mapToResponse(OrderEntity entity) {
        List<ItemResponseDTO> itemResponses = entity.getItems().stream()
                .map(item -> new ItemResponseDTO(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice()
                )).collect(Collectors.toList());

        return new OrderResponseDTO(
                entity.getOrderId(),
                entity.getValue(),
                entity.getCreationDate(),
                itemResponses
        );
    }
}