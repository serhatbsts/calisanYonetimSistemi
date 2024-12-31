package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.departmanlar;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface departmanRepository extends JpaRepository<departmanlar, Long> {
}
