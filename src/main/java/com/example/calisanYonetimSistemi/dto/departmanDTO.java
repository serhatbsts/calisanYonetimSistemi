package com.example.calisanYonetimSistemi.dto;

import lombok.Data;

import java.util.List;

@Data
public class departmanDTO {
    private Long id;
    private String departmanAdi;
    private String adres;
    private List<String> calisanAdSoyadListesi;
}
