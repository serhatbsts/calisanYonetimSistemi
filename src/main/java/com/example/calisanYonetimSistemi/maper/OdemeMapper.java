package com.example.calisanYonetimSistemi.maper;


import com.example.calisanYonetimSistemi.dto.OdemeDTO;
import com.example.calisanYonetimSistemi.model.odemeler;
import org.springframework.stereotype.Component;

@Component
public class OdemeMapper {

    public static OdemeDTO toOdemeDTO(odemeler odeme) {
        if (odeme == null) {
            return null;
        }

        OdemeDTO dto = new OdemeDTO();
        dto.setId(odeme.getId());
        dto.setOdemeTarih(odeme.getOdemeTarih());
        dto.setNotlar(odeme.getNotlar());
        dto.setToplamOdeme(odeme.getToplamOdeme());
        dto.setCalisanId(odeme.getCalisan().getId());
        dto.setCalisanAd(odeme.getCalisan().getAd());
        dto.setCalisanSoyad(odeme.getCalisan().getSoyAd());

        return dto;
    }


    public odemeler toOdemeEntity(OdemeDTO dto) {
        if (dto == null) {
            return null;
        }

        odemeler odeme = new odemeler();
        odeme.setId(dto.getId());
        odeme.setOdemeTarih(dto.getOdemeTarih());
        odeme.setNotlar(dto.getNotlar());
        odeme.setToplamOdeme(dto.getToplamOdeme());

        // Eğer DTO'da çalışan bilgisi varsa ayarlayın
        if (dto.getCalisanId() != null) {
            odeme.setCalisan(new com.example.calisanYonetimSistemi.model.calisanlar());
            odeme.getCalisan().setId(dto.getCalisanId());
        }

        return odeme;
    }
}