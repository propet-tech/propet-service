package br.com.senai.propetservice.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.senai.propetservice.models.enums.ServiceStatus;
import lombok.Data;

@Data
public class PetShopServiceDto implements Serializable {

    private Long id;
    private UUID userId;
    private Long petId;
    private String description;
    private Boolean active;
    private LocalDateTime startDateTime;
    private LocalDateTime doneDateTime;
    private ServiceStatus status;
}
