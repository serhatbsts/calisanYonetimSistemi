package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.model.iseGiris;
import com.example.calisanYonetimSistemi.repository.iseGiriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class iseGirisService {

    final iseGiriseRepository iseGiriseRepository;
    @Autowired
    public iseGirisService(iseGiriseRepository iseGiriseRepository) {
        this.iseGiriseRepository = iseGiriseRepository;
    }

    public List<iseGiris> getAllIseGiris() {
        return iseGiriseRepository.findAll();
    }

    public iseGiris saveIseGiris(iseGiris iseGiris) {
        return iseGiriseRepository.save(iseGiris);
    }
}
