package com.storage.storagedemo.Controllers;

import com.storage.storagedemo.Dto.Requests.ProductRequestDto;
import com.storage.storagedemo.Dto.Responses.ProductResponseDto;
import com.storage.storagedemo.Models.Product;
import com.storage.storagedemo.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(
            @RequestBody ProductRequestDto request){
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(
            @RequestParam Integer productId,
            @RequestBody ProductRequestDto request
    ){
        return new ResponseEntity<>(productService.updateProduct(productId, request), HttpStatus.OK);
    }
}
