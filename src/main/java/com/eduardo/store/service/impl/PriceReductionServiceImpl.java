package com.eduardo.store.service.impl;

import com.eduardo.store.dto.PriceReductionDTO;
import com.eduardo.store.model.PriceReduction;
import com.eduardo.store.repo.PriceReductionRepository;
import com.eduardo.store.service.IPriceReductionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PriceReductionServiceImpl implements IPriceReductionService {

    @Autowired
    private PriceReductionRepository priceReductionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PriceReduction convertToPojo(PriceReductionDTO priceReductionDTO) {
        return modelMapper.map(priceReductionDTO, PriceReduction.class);
    }
}
