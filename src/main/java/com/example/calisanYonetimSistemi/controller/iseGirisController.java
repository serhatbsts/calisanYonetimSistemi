package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.dto.iseGirisDTO;
import com.example.calisanYonetimSistemi.model.iseGiris;
import com.example.calisanYonetimSistemi.service.iseGirisService;
import com.example.calisanYonetimSistemi.repository.iseGiriseRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/iseGiris")
public class iseGirisController {

    private final iseGirisService iseGirisService;
    private final iseGiriseRepository iseGiriseRepository;

    @Autowired
    public iseGirisController(iseGirisService iseGirisService, com.example.calisanYonetimSistemi.repository.iseGiriseRepository iseGiriseRepository) {
        this.iseGirisService = iseGirisService;
        this.iseGiriseRepository = iseGiriseRepository;
    }
     @GetMapping("/byEmployee/{employeeId}")
    public ResponseEntity<List<iseGiris>> getIseGirisByEmployee(@PathVariable Long employeeId) {
        List<iseGiris> iseGirisKayitlari = iseGiriseRepository.findByCalisanId(employeeId);
        return ResponseEntity.ok(iseGirisKayitlari);
    }




    // Tüm işe girişleri getir
    @GetMapping
    public ResponseEntity<List<iseGirisDTO>> getAllIseGiris() {
        List<iseGirisDTO> response = iseGiriseRepository.findAll().stream()
                .map(entry -> {
                    iseGirisDTO dto = new iseGirisDTO();
                    dto.setId(entry.getId());
                    dto.setGirisTarihi(entry.getGirisTarihi());
                    if (entry.getCalisan() != null) {
                        dto.setCalisanAd(entry.getCalisan().getAd());
                        dto.setCalisanSoyad(entry.getCalisan().getSoyAd());
                        dto.setPozisyon(entry.getCalisan().getPozisyon());
                        if (entry.getCalisan().getDepartman() != null) {
                            dto.setDepartmanAdi(entry.getCalisan().getDepartman().getDepartmanAdi());
                        }
                    }
                    return dto;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    // Yeni bir işe giriş kaydı ekle
    @PostMapping
    public ResponseEntity<iseGiris> saveIseGiris(@RequestBody iseGiris iseGiris) {
        iseGiris savedIseGiris = iseGirisService.saveIseGiris(iseGiris);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIseGiris);
    }

    // Belirli bir işe giriş kaydını getir
    @GetMapping("/{id}")
    public ResponseEntity<iseGiris> getIseGirisById(@PathVariable Long id) {
        return iseGirisService.getAllIseGiris()
                .stream()
                .filter(iseGiris -> iseGiris.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
