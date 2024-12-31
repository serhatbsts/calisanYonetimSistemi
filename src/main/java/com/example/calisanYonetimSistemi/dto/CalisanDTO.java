package com.example.calisanYonetimSistemi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalisanDTO {
    private Long id;
    private String ad;
    private String soyAd;
    private String pozisyon;
    private String telNo;
    private String email;
    private BigDecimal maas;
    private String departmanAdi;

}
