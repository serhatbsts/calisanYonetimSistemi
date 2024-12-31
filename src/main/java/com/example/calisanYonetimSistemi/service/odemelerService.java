package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.dto.OdemeDTO;
import com.example.calisanYonetimSistemi.maper.OdemeMapper;
import com.example.calisanYonetimSistemi.model.odemeler;
import com.example.calisanYonetimSistemi.repository.odemelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class odemelerService {

    private final odemelerRepository odemelerRepository;
    private final OdemeMapper odemeMapper;

    @Autowired
    public odemelerService(odemelerRepository odemelerRepository, OdemeMapper odemeMapper) {
        this.odemelerRepository = odemelerRepository;
        this.odemeMapper = odemeMapper;
    }

    public List<OdemeDTO> getAllOdemeler() {
        List<odemeler> odemelerList = odemelerRepository.findAll();
        return odemelerList.stream()
                .map(OdemeMapper::toOdemeDTO)
                .collect(Collectors.toList());
    }

    public OdemeDTO saveOdeme(OdemeDTO odemeDTO) {
        odemeler odeme = odemeMapper.toOdemeEntity(odemeDTO);
        odemeler savedOdeme = odemelerRepository.save(odeme);
        return odemeMapper.toOdemeDTO(savedOdeme);
    }

    public OdemeDTO getOdemeById(Long id) {
        return odemelerRepository.findById(id)
                .map(OdemeMapper::toOdemeDTO)
                .orElse(null);
    }
}
