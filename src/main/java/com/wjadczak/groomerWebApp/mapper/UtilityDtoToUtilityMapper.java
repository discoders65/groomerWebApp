package com.wjadczak.groomerWebApp.mapper;

import com.wjadczak.groomerWebApp.dto.UtilityDto;
import com.wjadczak.groomerWebApp.entity.UtilityEntity;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface UtilityDtoToUtilityMapper {

    UtilityDtoToUtilityMapper utilityDtoToUtilityMapper = Mappers.getMapper(UtilityDtoToUtilityMapper.class);
    UtilityEntity mapUtilityDtoToUtility(UtilityDto utilityDto);
    List<UtilityEntity> mapUtilityDtosToUtilities(List<UtilityDto> utilityDtos);

}
