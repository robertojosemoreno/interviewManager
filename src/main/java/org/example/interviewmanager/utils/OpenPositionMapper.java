package org.example.interviewmanager.utils;

import org.example.interviewmanager.dto.OpenPositionDTO;
import org.example.interviewmanager.repository.entity.OpenPosition;


public class OpenPositionMapper {

    public static OpenPositionDTO toDTO(OpenPosition openPosition){
        return new OpenPositionDTO(
                openPosition.getId(),
                openPosition.getLevel(),
                openPosition.getType(),
                CompanyMapper.toDTO(openPosition.getCompany())
        );
    }

    public static OpenPosition toEntity(OpenPositionDTO openPositionDTO) {
        OpenPosition openPosition = new OpenPosition();
        openPosition.setCompany(CompanyMapper.toEntity(openPositionDTO.company()));
        openPosition.setId(openPositionDTO.id());
        openPosition.setType(openPositionDTO.type());
        openPosition.setLevel(openPositionDTO.level());
        return openPosition;
    }
}
