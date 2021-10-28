package com.eduardo.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceReductionDTO {
    private Long priceReductionCode;

    private Double newPrice;

    private Date startDate;

    private Date endDate;
}
