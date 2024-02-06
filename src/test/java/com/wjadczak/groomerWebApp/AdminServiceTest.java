package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.errors.EntityNotFoundException;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.service.implementation.AdminServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private ImageRepository imageRepositoryMock;
    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void shouldThrowEntityNotFoundExceptionIfImageIdNotFound(){
        // given
        UUID testId = TestUtils.NON_EXSITENT_IMAGE_ID;
        // when
        when(imageRepositoryMock.findById(testId)).thenReturn(Optional.empty());
        // then
        Assertions.assertThrows(EntityNotFoundException.class, () -> adminService.downloadImage(testId));
    }
}
