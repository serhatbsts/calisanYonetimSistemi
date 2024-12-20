package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.departmanlar;
import com.example.calisanYonetimSistemi.repository.departmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class departmanService {
    final departmanRepository departmanRepository;

    @Autowired
    public departmanService(departmanRepository departmanRepository) {
        this.departmanRepository = departmanRepository;
    }

    public List<departmanlar> getAllDepartman() {
        return departmanRepository.findAll();
    }

    public departmanlar saveDepartman(departmanlar departman) {
        return departmanRepository.save(departman);
    }
}
