package com.yeahbutstill.mvc.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    @NotBlank
    @NotEmpty
    private String firstName;

    private String middleName;
    private String lastName;

    @NotBlank
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @NotEmpty
    private String phone;

    private List<String> hobbies;
    private CreateAddressRequest address; // nasted object of CreateAddressRequest
    private List<CreateSocialMediaRequest> socialMedias;

}
