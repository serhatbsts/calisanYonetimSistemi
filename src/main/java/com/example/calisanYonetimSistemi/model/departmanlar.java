package com.example.calisanYonetimSistemi.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="departmanlar")
public class departmanlar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String departmanAdi;
    String adres;

}
