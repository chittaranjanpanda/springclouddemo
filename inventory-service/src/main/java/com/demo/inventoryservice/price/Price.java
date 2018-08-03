package com.demo.inventoryservice.price;

import lombok.Data;

import java.io.Serializable;

@Data
public class Price implements Serializable {
    private Long id;
    private Long productId;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double taxAmount;
    private String currency;
}
