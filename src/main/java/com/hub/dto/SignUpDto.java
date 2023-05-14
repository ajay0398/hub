package com.hub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotNull(message = "User name should not be null ")
    private String userName;
    @NotNull(message = "Password should not be null")
    private String password;
    @Email
    @NotNull(message = "Email should not be null")
    private String email;
}
