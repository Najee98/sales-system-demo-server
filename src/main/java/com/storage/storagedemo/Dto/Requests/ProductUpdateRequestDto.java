package com.storage.storagedemo.Dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequestDto implements Serializable {

    private Integer productId;
    private Integer quantity;
    private Double price;

}
