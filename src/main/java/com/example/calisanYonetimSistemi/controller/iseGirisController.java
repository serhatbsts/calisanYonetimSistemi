package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.model.iseGiris;
import com.example.calisanYonetimSistemi.service.iseGirisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iseGiris")
public class iseGirisController {

    private final iseGirisService iseGirisService;

    @Autowired
    public iseGirisController(iseGirisService iseGirisService) {
        this.iseGirisService = iseGirisService;
    }

    // Tüm işe girişleri getir
    @GetMapping
    public ResponseEntity<List<iseGiris>> getAllIseGiris() {
        List<iseGiris> iseGirisList = iseGirisService.getAllIseGiris();
        return ResponseEntity.ok(iseGirisList);
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
