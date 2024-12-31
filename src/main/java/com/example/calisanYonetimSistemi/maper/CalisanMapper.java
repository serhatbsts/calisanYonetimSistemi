package com.example.calisanYonetimSistemi.maper;


import com.example.calisanYonetimSistemi.dto.CalisanDTO;
import com.example.calisanYonetimSistemi.model.calisanlar;
import org.springframework.stereotype.Component;

@Component
public class CalisanMapper {

    public static CalisanDTO toCalisanDTO(calisanlar calisan) {
        if (calisan == null) {
            return null;
        }

        CalisanDTO dto = new CalisanDTO();
        dto.setId(calisan.getId());
        dto.setAd(calisan.getAd());
        dto.setSoyAd(calisan.getSoyAd());
        dto.setPozisyon(calisan.getPozisyon());
        dto.setTelNo(calisan.getTelNo());
        dto.setEmail(calisan.getEmail());
        dto.setMaas(calisan.getMaas());
        dto.setDepartmanAdi(calisan.getDepartman() != null ? calisan.getDepartman().getDepartmanAdi() : null);
        return dto;
    }

    public calisanlar toCalisanEntity(CalisanDTO dto) {
        calisanlar calisan = new calisanlar();
        calisan.setAd(dto.getAd());
        calisan.setSoyAd(dto.getSoyAd());
        calisan.setPozisyon(dto.getPozisyon());
        calisan.setTelNo(dto.getTelNo());
        calisan.setEmail(dto.getEmail());
        calisan.setMaas(dto.getMaas());
        return calisan;
    }

}