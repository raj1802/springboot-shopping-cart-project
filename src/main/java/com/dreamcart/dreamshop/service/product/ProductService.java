package com.dreamcart.dreamshop.service.product;

import com.dreamcart.dreamshop.exception.ProductNotFoundException;
import com.dreamcart.dreamshop.model.Category;
import com.dreamcart.dreamshop.model.Product;
import com.dreamcart.dreamshop.repository.CategoryRepository;
import com.dreamcart.dreamshop.repository.ProductRepository;
import com.dreamcart.dreamshop.request.AddProductRequest;
import com.dreamcart.dreamshop.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Product addProduct(AddProductRequest request) {
        //check if the category is found in you DB
        //if yes , set it as the new product category
        //if no, then save it as a new category
        //then set it as new product category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));

    }


    private Product createProduct(AddProductRequest request, Category category) {

        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product Not found"));
    }

    @Override
    public void deleteProductById(Long id) {
         productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, ()->{throw new ProductNotFoundException("Product Not found");});

    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {


return productRepository.findById(productId)
        .map(existingProduct -> updateExistingProduct(existingProduct,request))
        .map(productRepository :: save)
        .orElseThrow(()->new ProductNotFoundException("Product not found!"));

    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {

        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setName(category.getName());

        return existingProduct;

    }



    @Override
    public List<Product> getAllProducts(String category) {
        return List.of();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByPrice(double price) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrandAndByName( String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long CountProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
}
