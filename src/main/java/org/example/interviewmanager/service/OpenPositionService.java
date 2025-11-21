package org.example.interviewmanager.service;

import org.example.interviewmanager.dto.OpenPositionDTO;
import org.example.interviewmanager.repository.CompanyRepository;
import org.example.interviewmanager.repository.OpenPositionRepository;
import org.example.interviewmanager.repository.entity.Company;
import org.example.interviewmanager.repository.entity.OpenPosition;
import org.example.interviewmanager.utils.CompanyMapper;
import org.example.interviewmanager.utils.OpenPositionMapper;
import org.example.interviewmanager.utils.exception.CompanyNotFoundException;
import org.example.interviewmanager.utils.exception.OpenPositionNotFoundException;
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
    private final CompanyRepository companyRepository;

    public OpenPositionService(
            OpenPositionRepository openPositionRepository,
            CompanyRepository companyRepository
    ) {
        this.openPositionRepository = openPositionRepository;
        this.companyRepository = companyRepository;
    }

    public OpenPositionDTO getOpenPositionById(UUID id) {
        return OpenPositionMapper.toDTO(
                openPositionRepository.findById(id).orElseThrow(() ->new OpenPositionNotFoundException("Open Position not found"))
        );
    }

    public List<OpenPositionDTO> getOpenPositionsByLevel(String level, Pageable pageable) {
        Page<OpenPosition> openPositions = openPositionRepository.findOpenPositionsByLevel(level, pageable);
        return openPositions.stream().map(OpenPositionMapper::toDTO).collect(Collectors.toList());

    }

    public List<OpenPositionDTO> getOpenPositions(Pageable pageable) {
        Page<OpenPosition> openPositions = openPositionRepository.findAll(pageable);
        return openPositions.stream().map(OpenPositionMapper::toDTO).collect(Collectors.toList());
    }

    public OpenPositionDTO saveOpenPosition(OpenPositionDTO openPositionDTO) {
        Optional<Company> companyOpt = companyRepository.findById(openPositionDTO.company().id());
        if(companyOpt.isEmpty()) {
            throw new CompanyNotFoundException("Company id: "+ openPositionDTO.company().id()+" not found");
        } else {
            OpenPositionDTO newOpenPositionDTO = new OpenPositionDTO(
                    openPositionDTO.id(),
                    openPositionDTO.level(),
                    openPositionDTO.type(),
                    CompanyMapper.toDTO(companyOpt.get())
            );
            OpenPosition openPosition = OpenPositionMapper.toEntity(newOpenPositionDTO);
            return OpenPositionMapper.toDTO(openPositionRepository.save(openPosition));
        }
    }

    public OpenPositionDTO updateOpenPosition(OpenPositionDTO openPositionDTO, UUID id) {
        OpenPosition openPosition = OpenPositionMapper.toEntity(openPositionDTO);
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
            return OpenPositionMapper.toDTO(openPositionRepository.save(openPositionDb));
        } else {
            throw new OpenPositionNotFoundException("OpenPosition not found");
        }

    }

    public void deleteOpenPosition(UUID id) {
        openPositionRepository.deleteById(id);
    }


}
