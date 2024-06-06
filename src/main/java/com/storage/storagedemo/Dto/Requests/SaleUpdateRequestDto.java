package com.storage.storagedemo.Dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleUpdateRequestDto implements Serializable {

//    List<Integer> productIds;

    List<ProductUpdateRequestDto> productUpdates;

}
