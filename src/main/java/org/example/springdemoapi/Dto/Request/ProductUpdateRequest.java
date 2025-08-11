package org.example.springdemoapi.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.NonFinal;

@Data
@AllArgsConstructor
@NonFinal
@Builder
public class ProductUpdateRequest {

    private String name;

    private String description;

    private String type;


    private double price;
}
