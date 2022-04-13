package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;

    private PasswordForm passwordForm;
}