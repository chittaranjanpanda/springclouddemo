package com.demo.pricingservice.price;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PriceRepository extends PagingAndSortingRepository<Price, Long> {
    Optional<Price> findByProductId(Long id);
}
