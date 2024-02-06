package com.wjadczak.groomerWebApp.mapper;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageToImageDtoMapper {
    ImageToImageDtoMapper imageToImageDtoMapper = Mappers.getMapper(ImageToImageDtoMapper.class);
    @Mapping(target = "userId", expression = "java(image.getUserEntity().getId())")
    ImageDto mapImagetoImageDto(ImageEntity image);

}