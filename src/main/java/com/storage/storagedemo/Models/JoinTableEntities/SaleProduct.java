package com.storage.storagedemo.Models.JoinTableEntities;

import com.storage.storagedemo.Models.Product;
import com.storage.storagedemo.Models.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleProduct {

    @EmbeddedId
    private SaleProductKey id = new SaleProductKey();

    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double price;

}
