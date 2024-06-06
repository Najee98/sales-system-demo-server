package com.storage.storagedemo.Controllers;

import com.storage.storagedemo.Dto.Requests.SaleRequestDto;
import com.storage.storagedemo.Dto.Requests.SaleUpdateRequestDto;
import com.storage.storagedemo.Dto.Responses.SaleResponseDto;
import com.storage.storagedemo.Models.Sale;
import com.storage.storagedemo.Services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping()
    public ResponseEntity<List<SaleResponseDto>> salesOperations(){
        return new ResponseEntity<>(saleService.getAllSales(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDto request){
        return new ResponseEntity<>(saleService.createSale(request), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Sale> updateSale(
            @RequestParam Integer saleId,
            @RequestBody SaleUpdateRequestDto request
            ){
        return new ResponseEntity<>(saleService.updateSale(saleId, request), HttpStatus.OK);
    }

}
