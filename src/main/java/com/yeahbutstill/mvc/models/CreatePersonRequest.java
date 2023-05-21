package com.yeahbutstill.mvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private CreateAddressRequest address; // nasted object of CreateAddressRequest

}
