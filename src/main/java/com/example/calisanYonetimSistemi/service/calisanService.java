package com.example.calisanYonetimSistemi.service;

import com.example.calisanYonetimSistemi.dto.CalisanDTO;
import com.example.calisanYonetimSistemi.dto.calisanCreateRequest;
import com.example.calisanYonetimSistemi.maper.CalisanMapper;
import com.example.calisanYonetimSistemi.model.departmanlar;
import com.example.calisanYonetimSistemi.dto.calisanUpdateRequest;
import com.example.calisanYonetimSistemi.model.calisanlar;
import com.example.calisanYonetimSistemi.model.iseGiris;
import com.example.calisanYonetimSistemi.repository.calisanRepository;
import com.example.calisanYonetimSistemi.repository.departmanRepository;
import com.example.calisanYonetimSistemi.repository.iseGiriseRepository;
import com.example.calisanYonetimSistemi.repository.odemelerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class calisanService {

    private final calisanRepository calisanRepository;
    private final iseGiriseRepository iseGirisRepository;
    private final odemelerRepository odemelerRepository;
    private final iseGirisService iseGirisService;
    private final iseGiriseRepository iseGiriseRepository;
    private final departmanRepository departmanRepository;

    @Autowired
    public calisanService(calisanRepository calisanRepository
            , iseGiriseRepository iseGirisRepository
            , odemelerRepository odemelerRepository
            , iseGirisService iseGirisService
            , iseGiriseRepository iseGiriseRepository, com.example.calisanYonetimSistemi.repository.departmanRepository departmanRepository) {
        this.calisanRepository = calisanRepository;
        this.iseGirisRepository = iseGirisRepository;
        this.odemelerRepository = odemelerRepository;
        this.iseGirisService = iseGirisService;
        this.iseGiriseRepository = iseGiriseRepository;
        this.departmanRepository = departmanRepository;
    }


    public calisanlar login(String email,String password){
        calisanlar calisan=calisanRepository.findByEmailAndPassword(email,password);
        return calisan;
    }
    public CalisanDTO loginAndGetDTO(String email, String password) {
        calisanlar calisan = calisanRepository.findByEmailAndPassword(email, password);
        return calisan != null ? CalisanMapper.toCalisanDTO(calisan) : null;
    }

    public List<CalisanDTO> getAllCalisanlar() {
        List<calisanlar> calisanlarList = calisanRepository.findAll();
        return calisanlarList.stream()
                .map(CalisanMapper::toCalisanDTO)
                .collect(Collectors.toList());
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

        calisanRepository.save(calisan);

        iseGiris iseGiris=new iseGiris();
        iseGiris.setCalisan(calisan);
        iseGiris.setGirisTarihi(LocalDate.now());
        iseGiriseRepository.save(iseGiris);


        return calisanRepository.save(calisan);
    }
    @Transactional
    public void deleteCalisan(Long calisanId) {
        // Önce bağlı verileri sil
        iseGirisRepository.deleteAllByCalisanId(calisanId);
        odemelerRepository.deleteAllByCalisanId(calisanId);
        // Çalışanı sil
        calisanRepository.deleteById(calisanId);
    }

   /* public calisanlar updateCalisan(Long calisanId , calisanUpdateRequest yeniCalisan) {
        Optional<calisanlar> calisan=calisanRepository.findById(calisanId);
        if (calisan.isPresent()){
            calisanlar guncelCalisan=calisan.get();
            guncelCalisan.setAd(yeniCalisan.getAd());
            guncelCalisan.setSoyAd(yeniCalisan.getSoyAd());
            guncelCalisan.setEmail(yeniCalisan.getEmail());
            guncelCalisan.setTelNo(yeniCalisan.getTelNo());
            guncelCalisan.setPozisyon(yeniCalisan.getPozisyon());
            guncelCalisan.setMaas(yeniCalisan.getMaas());
            calisanRepository.save(guncelCalisan);

            Optional<departmanlar> departman = departmanRepository.findById(yeniCalisan.getDepartmanId());
            departman.ifPresent(guncelCalisan::setDepartman);

            return guncelCalisan;
        }else{
            return null;
        }
    }

    */
   public calisanlar updateCalisan(Long calisanId, calisanUpdateRequest yeniCalisan) {
       calisanlar calisan = calisanRepository.findById(calisanId)
               .orElseThrow(() -> new RuntimeException("Çalışan bulunamadı"));

       calisan.setAd(yeniCalisan.getAd());
       calisan.setSoyAd(yeniCalisan.getSoyAd());
       calisan.setEmail(yeniCalisan.getEmail());
       calisan.setTelNo(yeniCalisan.getTelNo());
       calisan.setPozisyon(yeniCalisan.getPozisyon());
       calisan.setMaas(yeniCalisan.getMaas());

       if (yeniCalisan.getDepartmanId() != null) {
           departmanlar departman = departmanRepository.findById(yeniCalisan.getDepartmanId())
                   .orElseThrow(() -> new RuntimeException("Departman bulunamadı"));
           calisan.setDepartman(departman);
       }

       return calisanRepository.save(calisan);
   }


}
