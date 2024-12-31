package com.example.calisanYonetimSistemi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class iseGirisDTO {

    private Long id;
    private LocalDate girisTarihi;
    private String calisanAd;
    private String calisanSoyad;
    private String pozisyon;
    private String departmanAdi;
}
