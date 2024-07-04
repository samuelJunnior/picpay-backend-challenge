package br.com.samueljunnior.module.user.dto;

import br.com.samueljunnior.module.user.enums.UserTypeEnum;

public record UserDTO(
        Long id,
        String fullName,
        String document,
        String email,
        String password,
        UserTypeEnum type
) {}
