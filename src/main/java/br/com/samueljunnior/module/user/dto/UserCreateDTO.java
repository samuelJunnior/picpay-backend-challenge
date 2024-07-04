package br.com.samueljunnior.module.user.dto;

import br.com.samueljunnior.module.user.enums.UserTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(
        @NotEmpty String fullName,
        @NotEmpty String document,
        @NotEmpty @Email String email,
        @NotEmpty String password,
        @NotNull UserTypeEnum type
) {
}
