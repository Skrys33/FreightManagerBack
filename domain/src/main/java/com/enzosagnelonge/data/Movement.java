package com.enzosagnelonge.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Movement {
    public enum TypeMovement {
        In,
        Out
    }

    UUID id;
    LocalDateTime creationDateTime;
    String creationUser;
    LocalDateTime movementDateTime;
    String location;
    Goods goods;
    Warehouse fromWarehouse;
    Warehouse toWarehouse;
    String customsStatus;
    String referenceAuthorization;
    String typeAuthorization;
    TypeMovement typeMovement;
}
