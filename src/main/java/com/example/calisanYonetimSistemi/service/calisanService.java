package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.dto.CalisanDTO;
import com.example.calisanYonetimSistemi.dto.calisanCreateRequest;
import com.example.calisanYonetimSistemi.dto.calisanUpdateRequest;
import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.repository.calisanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class calisanService {

    private final calisanRepository calisanRepository;

    @Autowired
    public calisanService(calisanRepository calisanRepository) {
        this.calisanRepository = calisanRepository;
    }

    public CalisanDTO convertToDo(calisanlar calisan){
        CalisanDTO calisanDTO = new CalisanDTO();
        calisanDTO.setId(calisan.getId());
        calisanDTO.setAd(calisan.getAd());
        calisanDTO.setSoyAd(calisan.getSoyAd());
        calisanDTO.setEmail(calisan.getEmail());
        return calisanDTO;
    }

    public calisanlar login(String email,String password){
        calisanlar calisan=calisanRepository.findByEmailAndPassword(email,password);
        return calisan;
    }

    public List<calisanlar> getAllCalisanlar() {
        return calisanRepository.findAll();
    }


    public List<calisanlar> getAccessibleCalisanlar(Long calisanId) {
        calisanlar calisan = calisanRepository.findById(calisanId)
                .orElseThrow(() -> new IllegalArgumentException("Çalışan bulunamadı"));

        String pozisyon = calisan.getPozisyon();

        switch (pozisyon) {
            case "GENEL_MUDUR":
                return calisanRepository.findAll(); // Tüm çalışanlar
            case "YARDIMCI_MUDUR":
                return calisanRepository.findAllByPozisyonNot("GENEL_MUDUR"); // Genel müdür hariç
            case "DEPARTMAN_MUDUR":
                return calisanRepository.findAllByDepartman(calisan.getDepartman()); // Kendi departmanı
            case "YARDIMCI_DEPARTMAN_MUDUR":
                return calisanRepository.findAllByDepartmanAndPozisyonNot(calisan.getDepartman(), "DEPARTMAN_MUDUR");
            case "PERSONEL":
                return List.of(calisan); // Sadece kendisi
            default:
                throw new IllegalArgumentException("Geçersiz pozisyon");
        }
    }

    public List<calisanlar> getEmployeesByDepartment(Long departmanId) {
        return calisanRepository.findAllCalisanByDepartman(departmanId);
    }
    public calisanlar getCalisanById(Long id) {
        return calisanRepository.findById(id).orElse(null);
    }


    public calisanlar saveCalisan(calisanCreateRequest yeniCalisan) {
        if (calisanRepository.existsByEmail(yeniCalisan.getEmail())){
            throw new IllegalArgumentException("Bu e Posta adresi daha önce kullanıldı");
        }
        calisanlar calisan=new calisanlar();
        calisan.setAd(yeniCalisan.getAd());
        calisan.setSoyAd(yeniCalisan.getSoyAd());
        calisan.setEmail(yeniCalisan.getEmail());
        calisan.setPozisyon(yeniCalisan.getPozisyon());
        calisan.setTelNo(yeniCalisan.getTelNo());
        calisan.setPassword(yeniCalisan.getPassword());
        return calisanRepository.save(calisan);
    }
    public void deleteOneCalisan(Long userId) {
        calisanRepository.deleteById(userId);
    }

    public calisanlar updateCalisan(Long calisanId , calisanUpdateRequest yeniCalisan) {
        Optional<calisanlar> calisan=calisanRepository.findById(calisanId);
        if (calisan.isPresent()){
            calisanlar guncelCalisan=calisan.get();
            guncelCalisan.setAd(yeniCalisan.getAd());
            guncelCalisan.setSoyAd(yeniCalisan.getSoyAd());
            guncelCalisan.setEmail(yeniCalisan.getEmail());
            guncelCalisan.setTelNo(yeniCalisan.getTelNo());
            guncelCalisan.setPozisyon(yeniCalisan.getPozisyon());
            calisanRepository.save(guncelCalisan);
            return guncelCalisan;
        }else{
            return null;
        }
    }

}
