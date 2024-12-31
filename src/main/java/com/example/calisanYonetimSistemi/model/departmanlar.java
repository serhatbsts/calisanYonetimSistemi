package com.example.calisanYonetimSistemi.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="departmanlar")
public class departmanlar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String departmanAdi;
    String adres;

    @OneToMany(mappedBy = "departman", fetch = FetchType.LAZY)
   // @JsonManagedReference // Döngüsel bağımlılığı tamamlar
    private List<calisanlar> calisanlar;


}
