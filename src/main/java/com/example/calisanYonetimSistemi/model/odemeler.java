package com.example.calisanYonetimSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="odemeler")
public class odemeler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Date odemeTarih;
    String notlar;

    @ManyToOne
    @JoinColumn(name = "calisanlar_id")
    calisanlar calis;

}
