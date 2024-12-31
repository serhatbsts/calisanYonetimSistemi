package com.example.calisanYonetimSistemi.maper;
import com.example.calisanYonetimSistemi.dto.departmanDTO;
import com.example.calisanYonetimSistemi.model.departmanlar;
import org.springframework.stereotype.Component;

@Component
public class departmanMapper {

    public departmanDTO toDepartmanDTO(departmanlar departman) {
        if (departman == null) {
            return null;
        }

        departmanDTO dto = new departmanDTO();
        dto.setId(departman.getId());
        dto.setDepartmanAdi(departman.getDepartmanAdi());
        dto.setAdres(departman.getAdres());
        return dto;
    }

    public departmanlar toDepartmanEntity(departmanDTO dto) {
        departmanlar departman = new departmanlar();
        departman.setDepartmanAdi(dto.getDepartmanAdi());
        departman.setAdres(dto.getAdres());
        return departman;
    }
}