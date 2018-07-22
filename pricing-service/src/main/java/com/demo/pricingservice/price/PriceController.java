package com.demo.pricingservice.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ( "price" )
public class PriceController {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceController(final PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @GetMapping
    public ResponseEntity<List<Price>> getPrices() {
        return ResponseEntity.ok((List<Price>) priceRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPrice(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(priceRepository.findById(id).get());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Price> getPriceByProductId(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(priceRepository.findByProductId(id).get());
    }

}
