package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.model.odemeler;
import com.example.calisanYonetimSistemi.repository.odemelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class odemelerService {

    final odemelerRepository odemelerRepository;

    @Autowired
    public odemelerService(odemelerRepository odemelerRepository) {
        this.odemelerRepository = odemelerRepository;
    }

    public List<odemeler> getAllOdemeler() {
        return odemelerRepository.findAll();
    }

    public odemeler saveOdeme(odemeler odeme) {
        return odemelerRepository.save(odeme);
    }

}
