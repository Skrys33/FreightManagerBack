package com.enzosagnelonge.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Warehouse {
    String code;
    String label;
}
