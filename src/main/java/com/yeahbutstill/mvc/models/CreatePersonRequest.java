package com.yeahbutstill.mvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> hobbies;
    private CreateAddressRequest address; // nasted object of CreateAddressRequest
    private List<CreateSocialMediaRequest> socialMedias;

}
