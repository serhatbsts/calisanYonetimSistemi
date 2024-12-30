package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.model.departmanlar;
import com.example.calisanYonetimSistemi.service.departmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departmanlar")
public class departmanlarController {

    private final departmanService departmanService;
    
    @Autowired
    public departmanlarController(departmanService departmanService) {
        this.departmanService = departmanService;
    }

    // Tüm departmanları getir
    @GetMapping
    public ResponseEntity<List<departmanlar>> getAllDepartmanlar() {
        List<departmanlar> departmanList = departmanService.getAllDepartman();
        return ResponseEntity.ok(departmanList);
    }

    // Yeni bir departman ekle
    @PostMapping
    public ResponseEntity<departmanlar> saveDepartman(@RequestBody departmanlar departman) {
        departmanlar savedDepartman = departmanService.saveDepartman(departman);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartman);
    }

    // Belirli bir departmanın detaylarını getir
    @GetMapping("/{id}")
    public ResponseEntity<departmanlar> getDepartmanById(@PathVariable Long id) {
        return departmanService.getAllDepartman()
                .stream()
                .filter(departman -> departman.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
