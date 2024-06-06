package com.storage.storagedemo.Logging;

import com.storage.storagedemo.Models.JoinTableEntities.SaleProduct;
import com.storage.storagedemo.Models.Sale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaleTransactionLogger {

    private static final Logger logger = LoggerFactory.getLogger(SaleTransactionLogger.class);

    public void logUpdate(Sale sale) {
        logger.info("Sale transaction updated: ID={}, Creation Date={}, Last Modified={}, Seller={}, Total={}",
                sale.getId(), sale.getCreationDate(), sale.getLastModified(), sale.getSeller(), sale.getTotal());

        for (SaleProduct saleProduct : sale.getSaleProducts()) {
            logger.info("Sale Product: Product Name={}, Quantity={}, Price={}",
                    saleProduct.getProduct().getName(), saleProduct.getQuantity(), saleProduct.getPrice());
        }
    }
}

