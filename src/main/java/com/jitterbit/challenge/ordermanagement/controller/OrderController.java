package com.jitterbit.challenge.ordermanagement.controller;

import com.jitterbit.challenge.ordermanagement.dto.OrderRequestDTO;
import com.jitterbit.challenge.ordermanagement.dto.OrderResponseDTO;
import com.jitterbit.challenge.ordermanagement.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por expor os endpoints de gerenciamento de pedidos.
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Criar um novo pedido (Obrigatório)
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obter os dados do pedido (Obrigatório)
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String orderId) {
        OrderResponseDTO response = orderService.getOrderById(orderId);
        return ResponseEntity.ok(response);
    }

    // Listar todos os pedidos (Opcional)
    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDTO>> listAllOrders() {
        List<OrderResponseDTO> responses = orderService.listAllOrders();
        return ResponseEntity.ok(responses);
    }

    // Atualizar o pedido (Opcional)
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable String orderId, @RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = orderService.updateOrder(orderId, request);
        return ResponseEntity.ok(response);
    }

    // Deletar o pedido (Opcional)
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}