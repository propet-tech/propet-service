package br.com.senai.propetservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants.ComponentModel;

import br.com.senai.propetservice.data.request.OrderRequest;
import br.com.senai.propetservice.data.request.UpdateOrderRequest;
import br.com.senai.propetservice.data.response.OrderHistoryResponse;
import br.com.senai.propetservice.data.response.OrderResponse;
import br.com.senai.propetservice.models.PetShopOrderHistory;
import br.com.senai.propetservice.models.PetShopServiceOrder;

@Mapper(componentModel = ComponentModel.SPRING, uses = { PetMapper.class })
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "pet.id", source = "petId"),
            @Mapping(target = "service.id", source = "serviceId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    PetShopServiceOrder map(OrderRequest dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "dateTime", ignore = true),
            @Mapping(target = "petshopOrder.id", source = "orderId"),
    })
    PetShopOrderHistory map(UpdateOrderRequest dto);

    OrderResponse map(PetShopServiceOrder entity);

    @Mappings({
            @Mapping(target = "petshopOrderId", source = "petshopOrder.id")
    })
    OrderHistoryResponse map(PetShopOrderHistory entity);
}
