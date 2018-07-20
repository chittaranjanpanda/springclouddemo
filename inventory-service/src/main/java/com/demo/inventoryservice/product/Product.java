package com.demo.inventoryservice.product;

import com.demo.inventoryservice.category.Category;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity ( name = "PRODUCT" )
public class Product implements Serializable {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id;

    private String name;

    private int quantity;

    @ManyToOne ( cascade = CascadeType.ALL )
    private Category category;
}
