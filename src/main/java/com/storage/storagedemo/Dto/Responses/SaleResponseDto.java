package com.storage.storagedemo.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto implements Serializable {

    Integer id;
    Date creationDate;
    String client;
    String seller;
    double total;

}
