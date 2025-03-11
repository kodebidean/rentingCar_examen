package com.real_ia.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequest {
    private Long userId;
    private Long carId;
    private Double andrey_precioDia;
    private String andrey_comentario;
}
