package com.storage.storagedemo.Dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto implements Serializable {

    String name;
    String description;
    double price;
    Integer categoryId;

}
