package br.com.senai.propetservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.converters.OrderMapper;
import br.com.senai.propetservice.data.request.OrderRequest;
import br.com.senai.propetservice.data.request.UpdateOrderRequest;
import br.com.senai.propetservice.data.response.OrderHistoryResponse;
import br.com.senai.propetservice.data.response.OrderResponse;
import br.com.senai.propetservice.models.PetShopOrderHistory;
import br.com.senai.propetservice.models.enums.OrderStatus;
import br.com.senai.propetservice.repository.OrderHistoryRepo;
import br.com.senai.propetservice.repository.OrderRepo;
import br.com.senai.propetservice.repository.filters.OrderFilters;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderHistoryRepo historyRepo;

    @Autowired
    private SimpMessagingTemplate websocket;

    @Transactional
    public void createOrders(List<OrderRequest> orders, JwtAuthenticationToken principal) {
        for (OrderRequest orderRequest : orders) {
            var entity = orderMapper.map(orderRequest);
            entity.setUser(UUID.fromString(principal.getName()));
            final var saved = orderRepo.save(entity);
            var history = new PetShopOrderHistory();

            history.setStatus(OrderStatus.RECEIVED);
            history.setDateTime(LocalDateTime.now());
            history.setPetshopOrder(saved);

            historyRepo.save(history);
        }
    }

    @Transactional
    public void updateServiceStatus(UpdateOrderRequest dto) {
        var entity = orderMapper.map(dto);
        entity.setDateTime(LocalDateTime.now());
        entity = historyRepo.save(entity);

        final var orderId = entity.getPetshopOrder().getId();
        final var user = orderRepo.getReferenceById(orderId).getUser();

        websocket.convertAndSendToUser(user.toString(), "/pet", getOrderHistory(orderId));
    }

    public Page<OrderResponse> getAllOrders(Pageable pageable, JwtAuthenticationToken principal) {
        // TODO verficar usuarios
        return orderRepo.findAll(pageable).map((order) -> {
            return orderMapper.map(order);
        });
    }

    public List<OrderHistoryResponse> getOrderHistory(Long id) {
        // TODO verficar usuarios
        var filter = new OrderFilters().filterByOrderId(id);
        return historyRepo.findAll(filter, Sort.by(Sort.Direction.ASC, "dateTime"))
                .stream()
                .map(oh -> orderMapper.map(oh))
                .toList();
    }
}
