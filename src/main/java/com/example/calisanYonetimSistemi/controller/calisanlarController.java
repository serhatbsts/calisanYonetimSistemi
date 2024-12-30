package com.example.calisanYonetimSistemi.controller;

import com.example.calisanYonetimSistemi.dto.CalisanDTO;
import com.example.calisanYonetimSistemi.dto.calisanCreateRequest;
import com.example.calisanYonetimSistemi.dto.calisanLoginRequest;
import com.example.calisanYonetimSistemi.dto.calisanUpdateRequest;
import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.repository.calisanRepository;
import com.example.calisanYonetimSistemi.service.calisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calisanlar")
//@CrossOrigin(origins = "http://localhost:63342")
public class calisanlarController {

    private final calisanService calisanService;
    private final com.example.calisanYonetimSistemi.repository.calisanRepository calisanRepository;

    @Autowired
    public calisanlarController(calisanService calisanService, calisanRepository calisanRepository) {
        this.calisanService = calisanService;
        this.calisanRepository = calisanRepository;
    }

    // Tüm çalışanları getir
    @GetMapping
    public ResponseEntity<List<calisanlar>> getAllCalisanlar() {
        List<calisanlar> calisanlarList = calisanService.getAllCalisanlar();
        return ResponseEntity.ok(calisanlarList);
    }

    @GetMapping("/accessible")
    public ResponseEntity<List<calisanlar>> getAccessibleCalisanlar(@RequestParam Long calisanId) {
        List<calisanlar> accessibleCalisanlar = calisanService.getAccessibleCalisanlar(calisanId);
        return ResponseEntity.ok(accessibleCalisanlar);
    }

    //giriş kısmı
    @PostMapping("/login")
    public ResponseEntity<CalisanDTO> login(@RequestBody calisanLoginRequest calisanLoginRequest) {
        calisanlar calisan=calisanService.login(calisanLoginRequest.getEmail(),calisanLoginRequest.getPassword());
        if(calisan!=null) {
            CalisanDTO calisanDTO = calisanService.convertToDo(calisan);
            return ResponseEntity.ok(calisanDTO);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/createCalisan")
    public calisanlar createCalisan(@RequestBody calisanCreateRequest yeniCalisan) {
        return calisanService.saveCalisan(yeniCalisan);
    }

    @PutMapping("/{calisanId}")
    public ResponseEntity<calisanlar> calisanGucelle(@PathVariable Long calisanId, @RequestBody calisanUpdateRequest updateCalisan) {
        calisanlar guncelCalisan=calisanService.updateCalisan(calisanId,updateCalisan);
        if(guncelCalisan!=null) {
            return ResponseEntity.ok(guncelCalisan);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Belirli bir çalışanın detaylarını getir
 /*   @GetMapping("/{id}")
    public ResponseEntity<calisanlar> getCalisanById(@PathVariable Long id) {
        return calisanService.getAllCalisanlar()
                .stream()
                .filter(calisan -> calisan.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  */
    @GetMapping("/{userId}")
    public ResponseEntity<calisanlar> getCalisanById(@PathVariable Long userId) {
        calisanlar calisan = calisanService.getCalisanById(userId);

        if (calisan != null) {
            return ResponseEntity.ok(calisan);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @DeleteMapping("/{calisanId}")
    public ResponseEntity<String> deleteCalisan(@PathVariable Long calisanId) {
        calisanService.deleteOneCalisan(calisanId);
        return ResponseEntity.ok("Calişan Silindi!!!");
    }


    @GetMapping("/byDepartment/{departmanId}")
    public ResponseEntity<List<calisanlar>> getEmployeesByDepartment(@PathVariable Long departmanId) {
        List<calisanlar> employees = calisanService.getEmployeesByDepartment(departmanId);
        return ResponseEntity.ok(employees);
    }
}
