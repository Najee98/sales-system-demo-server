package com.storage.storagedemo.Repositories;

import com.storage.storagedemo.Dto.Responses.ProductResponseDto;
import com.storage.storagedemo.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select new com.storage.storagedemo.Dto.Responses.ProductResponseDto(" +
            "p.id," +
            "p.name," +
            "p.description," +
            "c.name," +
            "p.creationDate) " +
            "from Product p join p.category c")
    List<ProductResponseDto> findAllProducts();
}
