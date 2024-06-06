package com.storage.storagedemo.Services;

import com.storage.storagedemo.Dto.Requests.SaleRequestDto;
import com.storage.storagedemo.Dto.Requests.SaleUpdateRequestDto;
import com.storage.storagedemo.Dto.Responses.SaleResponseDto;
import com.storage.storagedemo.Models.Sale;

import java.util.List;

public interface SaleService {

    List<SaleResponseDto> getAllSales();

    Sale createSale(SaleRequestDto request);

    Sale updateSale(Integer saleId, SaleUpdateRequestDto request);
}
