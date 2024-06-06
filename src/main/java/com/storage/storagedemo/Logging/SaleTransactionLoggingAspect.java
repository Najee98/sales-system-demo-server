package com.storage.storagedemo.Logging;

import com.storage.storagedemo.Dto.Requests.SaleUpdateRequestDto;
import com.storage.storagedemo.Models.Sale;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SaleTransactionLoggingAspect {

    @Autowired
    private SaleTransactionLogger saleTransactionLogger;

    @AfterReturning(pointcut = "execution(* com.storage.storagedemo.Services.SaleService.updateSale(..)) && args(saleId, request)", returning = "result")
    public void logUpdateSale(Integer saleId, SaleUpdateRequestDto request, Sale result) {
        saleTransactionLogger.logUpdate(result);
    }
}
