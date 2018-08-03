package com.demo.inventoryservice.price;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient ( name = "pricing-service", fallback = PriceServiceProxyFallback.class)
@RibbonClient ( name = "pricing-service" )
@Component
public interface PricingServiceProxy {
    @RequestMapping ( "/price/product/{id}" )
    Price findByProductId(@PathVariable ( value = "id" ) Long id);
}
