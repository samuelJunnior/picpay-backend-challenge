package br.com.samueljunnior.module.transfer.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateTransferDTO(
        @DecimalMin(value = "0.01", message = "Informe um valor maior que ZERO.")
        @NotNull(message = "Necessario informar um valor para tranferencia.")
        String value,
        @NotNull(message = "Pagador nao informado.")
        Long payer,
        @NotNull(message = "Recebedor nao informado.")
        Long payee
) {
}
