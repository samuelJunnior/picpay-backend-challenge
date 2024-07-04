package br.com.samueljunnior.module.user.dto;

import br.com.samueljunnior.module.user.enums.UserTypeEnum;

import java.math.BigDecimal;

public record UserDTO(
        Long id,
        String fullName,
        String document,
        String email,
        String password,
        UserTypeEnum type,
        BigDecimal balance
) {}
