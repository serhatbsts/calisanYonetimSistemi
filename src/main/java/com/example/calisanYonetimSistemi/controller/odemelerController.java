package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.dto.OdemeDTO;
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
    public ResponseEntity<List<OdemeDTO>> getAllOdemeler() {
        List<OdemeDTO> odemelerList = odemelerService.getAllOdemeler();
        return ResponseEntity.ok(odemelerList);
    }

    // Yeni bir ödeme ekle
    @PostMapping
    public ResponseEntity<OdemeDTO> saveOdeme(@RequestBody OdemeDTO odemeDTO) {
        OdemeDTO savedOdeme = odemelerService.saveOdeme(odemeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOdeme);
    }

    // Belirli bir ödemeyi getir
    @GetMapping("/{id}")
    public ResponseEntity<OdemeDTO> getOdemeById(@PathVariable Long id) {
        OdemeDTO odeme = odemelerService.getOdemeById(id);
        if (odeme != null) {
            return ResponseEntity.ok(odeme);
        }
        return ResponseEntity.notFound().build();
    }
}
