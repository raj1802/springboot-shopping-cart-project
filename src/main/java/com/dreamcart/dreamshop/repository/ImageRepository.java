package com.dreamcart.dreamshop.repository;

import com.dreamcart.dreamshop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
