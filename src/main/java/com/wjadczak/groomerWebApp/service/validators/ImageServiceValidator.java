package com.wjadczak.groomerWebApp.service.validators;

import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class ImageServiceValidator {
    public void validateImageFile(MultipartFile imageFile) {

        if (imageFile == null) {
            log.error("Image file is null");
            throw new IllegalArgumentException(ErrorMessages.NULL_IMAGE);
        } else if (imageFile.getSize() > 10 * 1024 * 2024) {
            log.error("Image file too large: " + imageFile.getSize());
            throw new IllegalArgumentException(ErrorMessages.IMAGE_TOO_LARGE);
        }

    }
}
