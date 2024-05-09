package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.dto.UtilityDto;
import com.wjadczak.groomerWebApp.entity.UtilityEntity;
import com.wjadczak.groomerWebApp.errors.EntityNotFoundException;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.mapper.UtilityDtoToUtilityMapper;
import com.wjadczak.groomerWebApp.mapper.UtilityToUtilityDtoMapper;
import com.wjadczak.groomerWebApp.repository.UtilityRepository;
import com.wjadczak.groomerWebApp.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;
    @Override
    public List<UtilityDto> getAllUtilities() {
        List<UtilityEntity> utilityEntities = utilityRepository.findAll();
        List<UtilityDto> utilityDtos = UtilityToUtilityDtoMapper.utilityToUtilityDtoMapper.mapUtilitiesToDtos(utilityEntities);
        return utilityDtos;
    }

    @Override
    public UtilityDto getUtilityById(UUID id) {
        UtilityEntity utility = utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.INVALID_ID));;
        UtilityDto utilityDto = UtilityToUtilityDtoMapper.utilityToUtilityDtoMapper.mapUtilityToDto(utility);
        return utilityDto;
    }

    @Override
    public void addUtility(UtilityDto utilityDto) {
        UtilityEntity utility = UtilityDtoToUtilityMapper.utilityDtoToUtilityMapper.mapUtilityDtoToUtility(utilityDto);
        utilityRepository.save(utility);
    }

    @Override
    public void deleteUtility(UUID id) {
        UtilityEntity utility = utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.INVALID_ID));
        utilityRepository.delete(utility);
    }
}
