package com.wjadczak.groomerWebApp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignUpDto {
    private String name;
    private String userName;
    private String email;
    private String password;
    private int mobile;
}
