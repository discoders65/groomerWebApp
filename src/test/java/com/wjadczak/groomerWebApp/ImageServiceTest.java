package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.config.security.AuthenticationHelper;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.ImageAlreadyExistsException;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.service.implementation.ImageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

    @Mock
    private ImageRepository imageRepositoryMock;
    @Mock
    private AuthenticationHelper authenticationHelper;
    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void shouldThrowImageAlreadyExistsIfCurrentUserAlreadyHaveImageInDatabase(){
        // given
        UserEntity mockUser = new UserEntity();
        mockUser.setId(TestUtils.NON_EXSITENT_USER_ID);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imageFile",
                "test.jpg",
                "image/jpeg",
                "test data".getBytes());
        // when
        when(authenticationHelper.getCurrentUser()).thenReturn(mockUser);
        when(imageRepositoryMock
                .findImageIdByUserId(mockUser
                        .getId())).thenReturn(Optional.of(TestUtils.NON_EXSITENT_USER_ID));
        // then
        Assertions.assertThrows(ImageAlreadyExistsException.class, () -> imageService.uploadImage(mockMultipartFile));
    }

}
