package com.storage.storagedemo.Dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto implements Serializable {

    String firstName;
    String lastName;
    String mobile;

}
