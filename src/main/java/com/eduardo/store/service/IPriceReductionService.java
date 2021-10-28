package com.eduardo.store.service;

import com.eduardo.store.dto.PriceReductionDTO;
import com.eduardo.store.model.PriceReduction;

public interface IPriceReductionService {
    PriceReduction convertToPojo(PriceReductionDTO priceReductionDTO);
}
