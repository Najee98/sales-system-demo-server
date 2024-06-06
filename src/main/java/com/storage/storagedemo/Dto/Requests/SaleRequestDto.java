package com.storage.storagedemo.Dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDto implements Serializable {

    //List<Integer> productIds;
    List<ProductUpdateRequestDto> products;
    String seller;
    Integer clientId;

}
