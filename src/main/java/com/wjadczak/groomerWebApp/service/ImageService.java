package com.wjadczak.groomerWebApp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
        String uploadImage(MultipartFile imageData) throws IOException;
}
