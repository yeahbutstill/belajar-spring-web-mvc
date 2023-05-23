package com.yeahbutstill.mvc.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record User(
        @NotEmpty @NotBlank
        String username,

        @NotEmpty @NotBlank
        String password
) {}
