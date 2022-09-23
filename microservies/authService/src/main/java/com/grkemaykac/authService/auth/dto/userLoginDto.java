package com.grkemaykac.authService.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@NotNull(message = "Request can not null!")
public class userLoginDto {

    private String username;
    private String password;
}
