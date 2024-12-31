package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.dto.departmanDTO;
import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.departmanlar;
import com.example.calisanYonetimSistemi.repository.calisanRepository;
import com.example.calisanYonetimSistemi.repository.departmanRepository;
import com.example.calisanYonetimSistemi.service.departmanService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/departmanlar")
public class departmanlarController {

    private final departmanService departmanService;
    private final calisanRepository calisanRepository;

    @Autowired
    public departmanlarController(departmanService departmanService, calisanRepository calisanRepository) {
        this.departmanService = departmanService;
        this.calisanRepository = calisanRepository;
    }
/*
    @GetMapping
    public ResponseEntity<List<departmanDTO>> getAllDepartmanlar(@RequestParam(value = "userId") Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        calisanlar user = calisanRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (!user.getPozisyon().equals("GENEL_MUDUR") && !user.getPozisyon().equals("YARDIMCI_MUDUR")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<departmanDTO> departmanList = departmanService.getAllDepartmanlar();
        return ResponseEntity.ok(departmanList);
    }

 */
@GetMapping
public ResponseEntity<List<departmanDTO>> getAllDepartmanlar(@RequestParam(value = "userId") Long userId) {
    calisanlar user = calisanRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

    List<String> authorizedRoles = Arrays.asList("GENEL_MUDUR", "ADMIN");

    if (!authorizedRoles.contains(user.getPozisyon())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    List<departmanDTO> departmanList = departmanService.getAllDepartmanlar();
    return ResponseEntity.ok(departmanList);
}


    @PostMapping
    public ResponseEntity<departmanDTO> saveDepartman(@RequestBody departmanDTO departmanDTO) {
        departmanDTO savedDepartman = departmanService.saveDepartman(departmanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartman);
    }

    @GetMapping("/{id}")
    public ResponseEntity<departmanDTO> getDepartmanById(@PathVariable Long id) {
        departmanDTO departman = departmanService.getDepartmanById(id);
        return ResponseEntity.ok(departman);
    }

    @PutMapping("/{id}")
    public ResponseEntity<departmanDTO> updateDepartman(@PathVariable Long id, @RequestBody departmanDTO updatedDepartman) {
        departmanDTO updatedDTO = departmanService.updateDepartman(id, updatedDepartman);
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartman(@PathVariable Long id) {
        departmanService.deleteDepartman(id);
        return ResponseEntity.noContent().build();
    }
}
