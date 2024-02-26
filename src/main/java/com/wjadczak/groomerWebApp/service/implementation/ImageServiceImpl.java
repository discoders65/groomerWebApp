package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.config.security.AuthenticationHelper;
import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.entity.ImageEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.EntityNotFoundException;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.ImageAlreadyExistsException;
import com.wjadczak.groomerWebApp.mapper.ImageToImageDtoMapper;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.service.ImageService;
import com.wjadczak.groomerWebApp.service.validators.ImageServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageServiceValidator imageServiceValidator;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public void uploadImage(MultipartFile imageFile) throws IOException {
        UserEntity currentUser = authenticationHelper.getCurrentUser();
        boolean imageExists = imageRepository.findImageIdByUserId(currentUser.getId()).isPresent();

        if(!imageExists){
            imageServiceValidator.validateImageFile(imageFile);
            ImageEntity imageSaved = ImageEntity.builder()
                    .name(imageFile.getOriginalFilename())
                    .type(imageFile.getContentType())
                    .userEntity(currentUser)
                    .imageData(imageFile.getBytes())
                    .build();

            imageRepository.save(imageSaved);
        } else {
            log.error("User with id: " + currentUser.getId() + " already uploaded image");
            throw new ImageAlreadyExistsException(ErrorMessages.IMAGE_ALREADY_EXISTS);
        }
    }

    @Override
    public ImageDto downloadCurrentUserImage() {
        ImageDto image = ImageToImageDtoMapper.imageToImageDtoMapper
                .mapImagetoImageDto(imageRepository
                        .findImageByUserId(authenticationHelper.getCurrentUser().getId())
                        .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.IMAGE_NOT_FOUND)));
        return image;
    }

}

