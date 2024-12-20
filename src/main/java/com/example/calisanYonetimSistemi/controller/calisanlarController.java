package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.service.calisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calisanlar")
public class calisanlarController {

    private final calisanService calisanService;

    @Autowired
    public calisanlarController(calisanService calisanService) {
        this.calisanService = calisanService;
    }

    // Tüm çalışanları getir
    @GetMapping
    public ResponseEntity<List<calisanlar>> getAllCalisanlar() {
        List<calisanlar> calisanlarList = calisanService.getAllCalisanlar();
        return ResponseEntity.ok(calisanlarList);
    }

    // Yeni bir çalışan ekle
    @PostMapping
    public ResponseEntity<calisanlar> saveCalisan(@RequestBody calisanlar calisan) {
        calisanlar savedCalisan = calisanService.saveCalisan(calisan);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCalisan);
    }

    // Belirli bir çalışanın detaylarını getir
    @GetMapping("/{id}")
    public ResponseEntity<calisanlar> getCalisanById(@PathVariable Long id) {
        return calisanService.getAllCalisanlar()
                .stream()
                .filter(calisan -> calisan.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
