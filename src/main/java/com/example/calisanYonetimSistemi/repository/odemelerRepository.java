package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.odemeler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface odemelerRepository extends JpaRepository<odemeler,Long>{
}
