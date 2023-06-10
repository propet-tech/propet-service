package br.com.senai.propetservice.data.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.senai.propetservice.models.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderHistoryResponse implements Serializable {

    private Long id;
    private LocalDateTime dateTime;
    private OrderStatus status;
    private Long petshopOrderId;
}
