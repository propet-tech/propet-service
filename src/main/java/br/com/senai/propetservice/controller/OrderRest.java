package br.com.senai.propetservice.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.propetservice.data.request.OrderRequest;
import br.com.senai.propetservice.data.request.UpdateOrderRequest;
import br.com.senai.propetservice.data.response.OrderHistoryResponse;
import br.com.senai.propetservice.data.response.OrderResponse;
import br.com.senai.propetservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/order")
@RestController
@Tag(name = "Order", description = "Order manager endpoint")
@Slf4j
public class OrderRest {

    @Autowired
    private OrderService orderService;

    @SubscribeMapping("/user/pet/{id}")
	public List<OrderHistoryResponse> greeting(@DestinationVariable("id") Long id,JwtAuthenticationToken principal) {
        return orderService.getOrderHistory(id);
	}

    @PostMapping
    @Operation(summary = "Create multiple orders")
    public void createOrders(@RequestBody List<OrderRequest> orders, JwtAuthenticationToken principal) {
        log.info("New Order received");
        orderService.createOrders(orders, principal);
    }

    @PostMapping("/status")
    @Operation(summary = "Update Order status")
    @RolesAllowed("admin")
    public void updateOrderStatus(@RequestBody UpdateOrderRequest dto) {
        orderService.updateServiceStatus(dto);
    }

    @GetMapping
    @Operation(summary = "Get orders")
    public Page<OrderResponse> getAll(@ParameterObject @PageableDefault Pageable pageable,
            JwtAuthenticationToken principal) {
        return orderService.getAllOrders(pageable, principal);
    }

}
