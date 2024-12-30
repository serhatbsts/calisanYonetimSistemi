package com.example.calisanYonetimSistemi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class calisanCreateRequest {

    @Email(message = "")
    @NotEmpty(message = "")
    private String email;
    private String ad;
    private String soyAd;
    private String password;
    private String pozisyon;
    private String telNo;


}
