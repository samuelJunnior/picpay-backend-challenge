package br.com.samueljunnior.module.wallet.dto;

import java.math.BigDecimal;

public record WalletDTO(
        Long id,
        Long idUser,
        BigDecimal balance
) {
}
