package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.departmanlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface departmanRepository extends JpaRepository<departmanlar,Long> {
}
