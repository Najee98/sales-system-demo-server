package com.storage.storagedemo.Models.JoinTableEntities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SaleProductKey implements Serializable {
    private Integer saleId;
    private Integer productId;

    // Getters, setters, hashCode, equals
}
