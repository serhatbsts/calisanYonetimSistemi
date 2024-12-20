package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.calisanlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface calisanRepository extends JpaRepository<calisanlar,Long> {
}
