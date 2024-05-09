package com.wjadczak.groomerWebApp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
@Builder
@Getter
@Setter
public class UtilityDto {

    private String name;
    private String description;
    private BigDecimal lowPrice;
    private BigDecimal upPrice;
    private LocalTime executionTime;

}
