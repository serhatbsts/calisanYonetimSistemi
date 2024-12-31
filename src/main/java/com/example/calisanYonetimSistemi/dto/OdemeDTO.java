package com.example.calisanYonetimSistemi.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OdemeDTO {
    private Long id;
    private LocalDate odemeTarih;
    private String notlar;
    private BigDecimal toplamOdeme;
    private Long calisanId; // Çalışan ID'sini eklemek için
    private String calisanAd; // Çalışan adını eklemek için
    private String calisanSoyad; // Çalışan soyadını eklemek için
}