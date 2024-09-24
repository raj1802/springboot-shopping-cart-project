package com.dreamcart.dreamshop.request;

import com.dreamcart.dreamshop.model.Category;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductUpdateRequest  {


    private Long id;
    private String name;
    private String brand;

    private BigDecimal price;
    private int inventory;
    private String description;

    private Category category;

}
