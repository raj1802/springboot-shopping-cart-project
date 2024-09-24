package com.dreamcart.dreamshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Category {

    @Id
    @GeneratedValue()
private Long id;
private String name;
private String description;



@OneToMany(mappedBy = "category")
private List<Product> products;

    public Category(String name) {
        this.name = name;
    }



}
