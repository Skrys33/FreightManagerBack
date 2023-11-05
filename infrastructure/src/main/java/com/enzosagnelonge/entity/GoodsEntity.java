package com.enzosagnelonge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class GoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    String referenceType;
    String reference;
    int quantity;
    double weight;
    int quantityTotalReference;
    double weightTotalReference;
    String description;
}
