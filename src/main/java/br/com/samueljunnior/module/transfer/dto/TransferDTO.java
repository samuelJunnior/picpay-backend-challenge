package br.com.samueljunnior.module.transfer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferDTO(
        Long id,
        Long idSender,
        Long idReceiver,
        LocalDateTime transferDate,
        BigDecimal value
) {
}
