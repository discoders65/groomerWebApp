package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.entity.ImageEntity;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public String uploadImage(MultipartFile imageFile) throws IOException {

        ImageEntity imageSaved = ImageEntity.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(imageFile.getBytes())
                .build();

        imageRepository.save(imageSaved);

        return "Successfully uploaded: " + imageFile.getOriginalFilename();
    }
}
