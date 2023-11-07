package com.enzosagnelonge.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Goods {
    String referenceType;
    String reference; 
    int quantity; 
    double weight;
    int quantityTotalReference; 
    double weightTotalReference; 
    String description;

    public boolean isReferenceValid(){
        return !referenceType.equals("AWB") || reference.length() == 11;
    }

    public boolean isQuantityValid(){
        return quantityTotalReference >= quantity;
    }

    public boolean isWeightValid(){
        return weightTotalReference >= weight;
    }
}
