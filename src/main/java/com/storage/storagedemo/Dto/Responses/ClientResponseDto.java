package com.storage.storagedemo.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto implements Serializable {

    Integer id;
    String firstName;
    String lastName;
    String mobile;

}
