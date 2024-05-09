package com.wjadczak.groomerWebApp.mapper;

import com.wjadczak.groomerWebApp.dto.UtilityDto;
import com.wjadczak.groomerWebApp.entity.UtilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UtilityToUtilityDtoMapper {

    UtilityToUtilityDtoMapper utilityToUtilityDtoMapper = Mappers.getMapper(UtilityToUtilityDtoMapper.class);
    UtilityDto mapUtilityToDto(UtilityEntity utilityEntity);
    List<UtilityDto> mapUtilitiesToDtos(List<UtilityEntity> utilityEntities);

}
