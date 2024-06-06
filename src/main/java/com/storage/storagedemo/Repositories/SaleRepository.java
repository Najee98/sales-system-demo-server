package com.storage.storagedemo.Repositories;

import com.storage.storagedemo.Dto.Responses.SaleResponseDto;
import com.storage.storagedemo.Models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query("select new com.storage.storagedemo.Dto.Responses.SaleResponseDto(" +
            "s.id," +
            "s.creationDate," +
            "concat(c.firstName, ' ', c.lastName)," +
            "s.seller," +
            "s.total ) " +
            "from Sale s join s.client c ")
    List<SaleResponseDto> findAllSales();
}
