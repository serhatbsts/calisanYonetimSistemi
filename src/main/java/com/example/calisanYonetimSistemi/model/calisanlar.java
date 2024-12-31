package com.example.calisanYonetimSistemi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departman_id")
    @JsonBackReference
    departmanlar departman;


    @OneToMany(mappedBy = "calisan", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<iseGiris> iseGirisList;

}
