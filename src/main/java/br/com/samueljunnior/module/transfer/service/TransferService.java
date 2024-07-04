package br.com.samueljunnior.module.transfer.service;

import br.com.samueljunnior.client.integrations.IntegrationsService;
import br.com.samueljunnior.core.exceptions.BusinessException;
import br.com.samueljunnior.module.transfer.dto.CreateTransferDTO;
import br.com.samueljunnior.module.transfer.dto.TransferDTO;
import br.com.samueljunnior.module.transfer.entity.TransferEntity;
import br.com.samueljunnior.module.transfer.mapper.TransferMapper;
import br.com.samueljunnior.module.transfer.repository.TransferRepository;
import br.com.samueljunnior.module.user.entity.UserEntity;
import br.com.samueljunnior.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final UserService userService;
    private final IntegrationsService integrationsService;
    private final TransferMapper transferMapper;

    @Transactional
    public TransferDTO transfer(CreateTransferDTO transfer){
        final var sender = userService.findUserById(transfer.payer());
        final var receiver = userService.findUserById(transfer.payee());

        final var value = new BigDecimal(transfer.value());
        this.validTransfer(sender, value);

        sender.debit(value);
        receiver.credit(value);

        final var entity = transferRepository.save(
                TransferEntity.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .transferDate(LocalDateTime.now())
                        .value(value)
                        .build()
        );

        CompletableFuture.runAsync(integrationsService::notification);

        return transferMapper.toDto(entity);
    }

    private void validTransfer(UserEntity sender, BigDecimal value) {
        if(sender.isMerchant()){
            throw new BusinessException("Transfer no allowed to merchant user.");
        }

        if(!sender.hasBalance(value)){
            throw new BusinessException("Insufficient balance");
        }

        if(!integrationsService.authorize()){
            throw new BusinessException("Transfer not authorize.");
        }
    }
}
