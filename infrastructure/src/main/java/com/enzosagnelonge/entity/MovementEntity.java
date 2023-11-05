package com.enzosagnelonge.entity;


import com.enzosagnelonge.data.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementEntity {
    public enum TypeMovement {
        IN,
        OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    LocalDateTime creationDateTime;
    String creationUser;
    LocalDateTime movementDateTime;
    String location;
    Goods goods;
    String customsStatus;
    String codeWarehouse;
    String labelWarehouse;
    String referenceAuthorization;
    String typeAuthorization;
    TypeMovement typeMovement;
}