package com.storage.storagedemo.Services;

import com.storage.storagedemo.Dto.Requests.ProductRequestDto;
import com.storage.storagedemo.Dto.Responses.ProductResponseDto;
import com.storage.storagedemo.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.storage.storagedemo.Models.Product;
import com.storage.storagedemo.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> products = productRepository.findAllProducts();

        return products;
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product with id: " + " not found")
                );
    }

    @Override
    public Product createProduct(ProductRequestDto request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(categoryService.getCategoryById(request.getCategoryId()));
        product.setCreationDate(new Date());
        product.setPrice(request.getPrice());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer productId, ProductRequestDto request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product with id: " + productId + " not found.")
                );

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(categoryService.getCategoryById(request.getCategoryId()));
        product.setLastModified(new Date());

        return productRepository.save(product);

    }
}
