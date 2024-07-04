package br.com.samueljunnior.module.user.dto;

import br.com.samueljunnior.module.user.enums.UserTypeEnum;

public record UserFilterDTO(
        String document,
        String email,
        UserTypeEnum type
) {}
