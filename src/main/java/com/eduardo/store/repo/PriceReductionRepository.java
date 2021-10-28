package com.eduardo.store.repo;

import com.eduardo.store.model.PriceReduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceReductionRepository extends JpaRepository<PriceReduction, Long> {
    List<PriceReduction> findByPriceReductionCode(Long priceReductionCode);
}
