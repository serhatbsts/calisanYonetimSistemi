package com.example.calisanYonetimSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="calisanlar")
public class calisanlar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String ad;
    String soyAd;
    String pozisyon;
    String telNo;
    String email;
    String password;
    BigDecimal maas;


    @ManyToOne
    @JoinColumn(name = "departman_id")
    departmanlar departman;



}
