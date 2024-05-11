package com.wjadczak.groomerWebApp.unit;

import com.wjadczak.groomerWebApp.dto.UtilityDto;
import com.wjadczak.groomerWebApp.entity.UtilityEntity;
import com.wjadczak.groomerWebApp.errors.EntityNotFoundException;
import com.wjadczak.groomerWebApp.repository.UtilityRepository;
import com.wjadczak.groomerWebApp.service.implementation.UtilityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilityServiceTest {

    @Mock
    private UtilityRepository utilityRepositoryMock;

    @InjectMocks
    private UtilityServiceImpl utilityService;

    @Test
    public void testGetAllUtilities() {

        UtilityEntity utility1 = new UtilityEntity(UUID.randomUUID(), "Utility 1");
        UtilityEntity utility2 = new UtilityEntity(UUID.randomUUID(), "Utility 2");
        List<UtilityEntity> mockUtilities = Arrays.asList(utility1, utility2);

        when(utilityRepositoryMock.findAll()).thenReturn(mockUtilities);

        List<UtilityDto> utilityDtos = utilityService.getAllUtilities();

        assertEquals(mockUtilities.size(), utilityDtos.size());
        assertEquals(mockUtilities.get(0).getName(), utilityDtos.get(0).getName());
        assertEquals(mockUtilities.get(1).getName(), utilityDtos.get(1).getName());
    }

    @Test
    public void testGetUtilityById() {

        UUID id = UUID.randomUUID();
        UtilityEntity mockUtility = new UtilityEntity(id, "Utility 1");

        when(utilityRepositoryMock.findById(id)).thenReturn(Optional.of(mockUtility));
        UtilityDto utilityDto = utilityService.getUtilityById(id);

        assertEquals(mockUtility.getName(), utilityDto.getName());
    }

    @Test
    public void testGetUtilityById_NotFound() {

        when(utilityRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> utilityService.getUtilityById(UUID.randomUUID()));
    }

    @Test
    public void testAddUtility() {

        UtilityDto utilityDto = UtilityDto.builder()
                .name("utility")
                .description("utility1")
                .executionTime(LocalTime.NOON)
                .lowPrice(BigDecimal.ONE)
                .upPrice(BigDecimal.TEN)
                .build();
        utilityService.addUtility(utilityDto);

        verify(utilityRepositoryMock, times(1)).save(any(UtilityEntity.class));
    }

    @Test
    public void testDeleteUtility() {

        UUID id = UUID.randomUUID();
        UtilityEntity mockUtility = new UtilityEntity(id, "Utility 1");

        when(utilityRepositoryMock.findById(id)).thenReturn(Optional.of(mockUtility));
        utilityService.deleteUtility(id);

        verify(utilityRepositoryMock, times(1)).delete(any(UtilityEntity.class));
    }

    @Test
    public void testDeleteUtility_NotFound() {

        when(utilityRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> utilityService.deleteUtility(UUID.randomUUID()));
    }

}