package com.grkemaykac.authService.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NotNull(message = "Request can not null!")
public class userDto {
    private Long id;
    @NotEmpty(message = "Username can not be empty!")
    private String username;
    @Email(message = "Email should be valid!")
    private String email;
    @NotEmpty(message = "Password can not be empty!")
    private String password;

}
