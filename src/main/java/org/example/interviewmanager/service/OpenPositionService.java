package org.example.interviewmanager.service;

import org.example.interviewmanager.dto.OpenPositionDTO;
import org.example.interviewmanager.repository.OpenPositionRepository;
import org.example.interviewmanager.repository.entity.OpenPosition;
import org.example.interviewmanager.utils.exception.OpenPositionNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OpenPositionService {

    private final OpenPositionRepository openPositionRepository;
    private final ModelMapper modelMapper;

    public OpenPositionService(OpenPositionRepository openPositionRepository, ModelMapper modelMapper) {
        this.openPositionRepository = openPositionRepository;
        this.modelMapper = modelMapper;
    }

    public OpenPositionDTO getOpenPositionById(UUID id) {
        return modelMapper.map(openPositionRepository.findById(id), OpenPositionDTO.class);
    }

    public List<OpenPositionDTO> getOpenPositionsByLevel(String level, Pageable pageable) {
        Page<OpenPosition> openPositions = openPositionRepository.findOpenPositionsByLevel(level, pageable);
        return openPositions.stream().map((element) -> modelMapper.map(element, OpenPositionDTO.class)).collect(Collectors.toList());

    }

    public List<OpenPositionDTO> getOpenPositions(Pageable pageable) {
        Page<OpenPosition> openPositions = openPositionRepository.findAll(pageable);
        return openPositions.stream().map((element) -> modelMapper.map(element, OpenPositionDTO.class)).collect(Collectors.toList());
    }

    public OpenPositionDTO saveOpenPosition(OpenPositionDTO openPositionDTO) {
        OpenPosition openPosition = modelMapper.map(openPositionDTO, OpenPosition.class);
        return modelMapper.map(openPositionRepository.save(openPosition), OpenPositionDTO.class);
    }

    public OpenPositionDTO updateOpenPosition(OpenPositionDTO openPositionDTO, UUID id) {
        OpenPosition openPosition = modelMapper.map(openPositionDTO, OpenPosition.class);
        Optional<OpenPosition> openPositionDbOptional = openPositionRepository.findById(id);
        if (openPositionDbOptional.isPresent()) {
            OpenPosition openPositionDb = openPositionDbOptional.get();
            if(Objects.nonNull(openPosition.getLevel()) &&
                    !openPosition.getLevel().isBlank() &&
                    !openPositionDb.getLevel().equals(openPosition.getLevel())) {
                openPositionDb.setLevel(openPosition.getLevel());
            }
            if(Objects.nonNull(openPosition.getType()) &&
                    !openPosition.getType().isBlank() &&
                    !openPositionDb.getType().equals(openPosition.getType())){
                openPositionDb.setType(openPosition.getType());
            }
            return modelMapper.map(openPositionRepository.save(openPositionDb), OpenPositionDTO.class);
        } else {
            throw new OpenPositionNotFoundException("OpenPosition not found");
        }

    }

    public void deleteOpenPosition(UUID id) {
        openPositionRepository.deleteById(id);
    }


}
