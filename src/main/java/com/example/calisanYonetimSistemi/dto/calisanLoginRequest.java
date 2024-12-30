package com.example.calisanYonetimSistemi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class calisanLoginRequest {
    @Email(message = "")
    @NotEmpty(message = "")
    private String email;

    @NotEmpty(message = "")
    private String password;
}
