package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.dto.UtilityDto;

import java.util.List;
import java.util.UUID;

public interface UtilityService {

    List<UtilityDto> getAllUtilities();
    UtilityDto getUtilityById(UUID id);
    void addUtility(UtilityDto utility);
    void deleteUtility(UUID id);

}
