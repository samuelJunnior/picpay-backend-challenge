package br.com.samueljunnior.module.wallet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record DepositValueDTO(
        @NotNull(message = "Informe o identificador do usuário para deposito.")
        Long idWallet,
        @DecimalMin(value = "0.01", message = "Informe um valor maior que ZERO.")
        @NotNull(message = "Necessario informar um valor para tranferencia.")
        String value
) {
}
