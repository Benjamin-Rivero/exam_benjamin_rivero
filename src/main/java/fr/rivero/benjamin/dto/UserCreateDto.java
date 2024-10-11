package fr.rivero.benjamin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserCreateDto {

    private String email;

    private String username;

    private LocalDate birthedAt;

    private String avatar;

    private String password;

    private String confirmedPassword;

}