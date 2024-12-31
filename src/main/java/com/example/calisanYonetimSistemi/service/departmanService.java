package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.dto.departmanDTO;
import com.example.calisanYonetimSistemi.maper.departmanMapper;
import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.departmanlar;
import com.example.calisanYonetimSistemi.repository.departmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class departmanService {

    private final departmanRepository departmanRepository;
    private final departmanMapper departmanMapper;

    @Autowired
    public departmanService(departmanRepository departmanRepository, departmanMapper departmanMapper) {
        this.departmanRepository = departmanRepository;
        this.departmanMapper = departmanMapper;
    }

    public List<departmanDTO> getAllDepartmanlar() {
        List<departmanlar> departmanlarList = departmanRepository.findAll();
        return departmanlarList.stream()
                .map(departmanMapper::toDepartmanDTO) // departmanMapper burada kullanılır
                .collect(Collectors.toList());
    }

    public departmanDTO saveDepartman(departmanDTO departmanDTO) {
        departmanlar departman = departmanMapper.toDepartmanEntity(departmanDTO);
        departmanlar savedDepartman = departmanRepository.save(departman);
        return departmanMapper.toDepartmanDTO(savedDepartman);
    }

    public departmanDTO getDepartmanById(Long id) {
        departmanlar departman = departmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı"));
        return departmanMapper.toDepartmanDTO(departman);
    }

    public departmanDTO updateDepartman(Long id, departmanDTO updatedDepartmanDTO) {
        departmanlar departman = departmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı"));

        departman.setDepartmanAdi(updatedDepartmanDTO.getDepartmanAdi());
        departman.setAdres(updatedDepartmanDTO.getAdres());

        departmanlar updatedDepartman = departmanRepository.save(departman);
        return departmanMapper.toDepartmanDTO(updatedDepartman);
    }

    public void deleteDepartman(Long id) {
        departmanlar departman = departmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı"));

        departmanRepository.delete(departman);
    }
}
