package com.storage.storagedemo.Services;

import com.storage.storagedemo.Dto.Requests.ProductRequestDto;
import com.storage.storagedemo.Dto.Responses.ProductResponseDto;
import com.storage.storagedemo.Models.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProducts();

    Product getProductById(Integer productId);

    Product createProduct(ProductRequestDto request);

    Product updateProduct(Integer productId, ProductRequestDto request);
}
