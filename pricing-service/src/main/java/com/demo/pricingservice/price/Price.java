package com.demo.pricingservice.price;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "PRICE")
public class Price implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double taxAmount;
    private String currency;
}
