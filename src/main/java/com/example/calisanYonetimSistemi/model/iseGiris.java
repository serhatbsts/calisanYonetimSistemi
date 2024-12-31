package com.example.calisanYonetimSistemi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name="iseGiris")
public class iseGiris {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDate girisTarihi;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "calisanlar_id", nullable = false)
    @JsonBackReference
    calisanlar calisan;


}
