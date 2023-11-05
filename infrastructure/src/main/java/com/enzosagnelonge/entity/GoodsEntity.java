package com.enzosagnelonge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class GoodsEntity {

    UUID id;
    String referenceType;
    String reference;
    int quantity;
    double weight;
    int quantityTotalReference;
    double weightTotalReference;
    String description;
}
