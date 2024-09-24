package com.dreamcart.dreamshop.service.product;

import com.dreamcart.dreamshop.model.Product;
import com.dreamcart.dreamshop.request.AddProductRequest;
import com.dreamcart.dreamshop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long id);
    List<Product> getAllProducts(String category);

    List<Product> getAllProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByPrice(double price);
    List<Product> getProductsByBrandAndByName(String brand, String name);
    Long CountProductsByBrandAndName(String category,String name);



}
