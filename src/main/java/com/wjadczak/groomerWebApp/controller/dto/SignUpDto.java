package com.wjadczak.groomerWebApp.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // TODO delete setter
@NoArgsConstructor //TODO ALLargs
public class SignUpDto {
    private Long id; // TODO all fields are final
    private String name;
    private String userName;
    private String email;
    private String password;
}
