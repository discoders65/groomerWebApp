package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.entity.ImageEntity;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.service.ImageService;
import com.wjadczak.groomerWebApp.service.validators.ImageServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageServiceValidator imageServiceValidator;

    @Override
    public void uploadImage(MultipartFile imageFile) throws IOException {

        imageServiceValidator.validateImageFile(imageFile);
        ImageEntity imageSaved = ImageEntity.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(imageFile.getBytes())
                .build();

        imageRepository.save(imageSaved);
    }

    @Override
    public ImageEntity downloadImage(UUID id) {
        ImageEntity image = imageRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Image Id not found"));
        return image;
    }
}

