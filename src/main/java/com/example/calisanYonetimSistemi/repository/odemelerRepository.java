package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.odemeler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface odemelerRepository extends JpaRepository<odemeler,Long>{
    List<odemeler> findByCalisan(calisanlar calisan);
    void deleteAllByCalisanId(Long calisanId);
}
