package com.example.calisanYonetimSistemi.repository;

import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.departmanlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface calisanRepository extends JpaRepository<calisanlar,Long> {

    boolean existsByEmail(String email);
    calisanlar findById(long id);
    calisanlar findByEmailAndPassword(String email, String password);
    Optional<calisanlar> findBypassword(String password);

    List<calisanlar> findAllByDepartman(departmanlar departman);

    @Query("SELECT c FROM calisanlar c WHERE c.departman.id = :departmanId")
    List<calisanlar> findAllCalisanByDepartman(@Param("departmanId")Long departmanId);

    List<calisanlar> findAllByDepartmanAndPozisyonNot(departmanlar departman, String pozisyon);

    List<calisanlar> findAllByPozisyonNot(String pozisyon);
}
