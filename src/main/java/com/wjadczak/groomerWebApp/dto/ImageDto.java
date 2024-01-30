package com.wjadczak.groomerWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDto {

    private UUID id;
    private String name;
    private String type;
    private UUID userId;
    private byte[] imageData;

}
