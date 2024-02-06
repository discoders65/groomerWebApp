package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ImageService {
        void uploadImage(MultipartFile imageData) throws IOException;
        ImageDto downloadImage(UUID id);
}
