package com.example.calisanYonetimSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@Table(name="odemeler")
public class odemeler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDate odemeTarih;
    String notlar;
    BigDecimal toplamOdeme;

    @ManyToOne
    @JoinColumn(name = "calisan_id")
    calisanlar calisan;

}
