package com.demo.inventoryservice.price;

import org.springframework.stereotype.Component;

@Component
public class PriceServiceProxyFallback implements PricingServiceProxy {
    @Override
    public Price findByProductId(Long id) {
        Price price = new Price();
        price.setId(0L);
        return price;
    }
}
