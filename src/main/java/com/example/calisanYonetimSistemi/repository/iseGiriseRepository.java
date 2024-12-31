package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.iseGiris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iseGiriseRepository extends JpaRepository<iseGiris,Long> {
    List<iseGiris> findByCalisan(calisanlar calisan);
    List<iseGiris> findByCalisanId(Long calisanId); // Çalışan ID'sine göre kayıt getir
    void deleteAllByCalisanId(Long calisanId);
}
