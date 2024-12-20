package com.example.calisanYonetimSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="iseGiris")
public class iseGiris {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Date tarih;

    @ManyToOne
    @JoinColumn(name = "calisanlar_id")
    calisanlar calis;


}
