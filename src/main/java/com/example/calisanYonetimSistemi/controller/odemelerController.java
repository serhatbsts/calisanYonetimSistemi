package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.model.odemeler;
import com.example.calisanYonetimSistemi.service.odemelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odemeler")
public class odemelerController {

    private final odemelerService odemelerService;

    @Autowired
    public odemelerController(odemelerService odemelerService) {
        this.odemelerService = odemelerService;
    }

    // Tüm ödemeleri getir
    @GetMapping
    public ResponseEntity<List<odemeler>> getAllOdemeler() {
        List<odemeler> odemelerList = odemelerService.getAllOdemeler();
        return ResponseEntity.ok(odemelerList);
    }

    // Yeni bir ödeme ekle
    @PostMapping
    public ResponseEntity<odemeler> saveOdeme(@RequestBody odemeler odeme) {
        odemeler savedOdeme = odemelerService.saveOdeme(odeme);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOdeme);
    }

    // Belirli bir ödemeyi getir
    @GetMapping("/{id}")
    public ResponseEntity<odemeler> getOdemeById(@PathVariable Long id) {
        return odemelerService.getAllOdemeler()
                .stream()
                .filter(odeme -> odeme.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
