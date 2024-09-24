package com.dreamcart.dreamshop.repository;

import com.dreamcart.dreamshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


     Category findByName(String name) ;

     boolean existsByName(String name);
}
