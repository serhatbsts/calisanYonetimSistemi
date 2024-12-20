package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.iseGiris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iseGiriseRepository extends JpaRepository<iseGiris,Long> {
}
