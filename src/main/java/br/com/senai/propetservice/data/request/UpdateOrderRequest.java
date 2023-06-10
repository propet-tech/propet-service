package br.com.senai.propetservice.data.request;

import java.io.Serializable;

import br.com.senai.propetservice.models.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderRequest implements Serializable {

    private Long orderId;
    private OrderStatus status;
}
