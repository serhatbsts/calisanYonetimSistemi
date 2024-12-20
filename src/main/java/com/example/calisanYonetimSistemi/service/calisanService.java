package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.repository.calisanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class calisanService {

    private final calisanRepository calisanRepository;

    @Autowired
    public calisanService(calisanRepository calisanRepository) {
        this.calisanRepository = calisanRepository;
    }

    public List<calisanlar> getAllCalisanlar() {
        return calisanRepository.findAll();
    }

    public calisanlar saveCalisan(calisanlar calisan) {
        return calisanRepository.save(calisan);
    }
}
