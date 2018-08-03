package com.demo.inventoryservice.product;

import com.demo.inventoryservice.price.Price;
import com.demo.inventoryservice.price.PricingServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ( "product" )
public class ProductController {

    private ProductRepository productRepository;

    private PricingServiceProxy pricingService;

    @Autowired
    public ProductController(ProductRepository productRepository, PricingServiceProxy pricingService) {
        this.productRepository = productRepository;
        this.pricingService = pricingService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok((List<Product>) productRepository.findAll());
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id).get());
    }

    @GetMapping ( "/{id}/price" )
    public ResponseEntity<Price> getPrice(@PathVariable Long id) {
        return ResponseEntity.ok(pricingService.findByProductId(id));
    }

}
