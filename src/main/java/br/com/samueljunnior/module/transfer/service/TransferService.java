package br.com.samueljunnior.module.transfer.service;

import br.com.samueljunnior.client.integrations.IntegrationsService;
import br.com.samueljunnior.core.exceptions.BusinessException;
import br.com.samueljunnior.module.transfer.dto.CreateTransferDTO;
import br.com.samueljunnior.module.transfer.dto.TransferDTO;
import br.com.samueljunnior.module.transfer.entity.TransferEntity;
import br.com.samueljunnior.module.transfer.mapper.TransferMapper;
import br.com.samueljunnior.module.transfer.repository.TransferRepository;
import br.com.samueljunnior.module.wallet.entity.WalletEntity;
import br.com.samueljunnior.module.wallet.service.WalletService;
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
    private final WalletService walletService;
    private final IntegrationsService integrationsService;
    private final TransferMapper transferMapper;

    @Transactional
    public TransferDTO transfer(CreateTransferDTO transfer){
        final var walletSender = walletService.getWalletByUser(transfer.payer());
        final var walletReceiver =walletService.getWalletByUser(transfer.payee());

        final var value = new BigDecimal(transfer.value());
        this.validTransfer(walletSender, value);

        walletService.debitValue(walletSender.getId(), value);
        walletService.creditValue(walletReceiver.getId(), value);

        final var entity = transferRepository.save(
                TransferEntity.builder()
                        .sender(walletSender.getUser())
                        .receiver(walletReceiver.getUser())
                        .transferDate(LocalDateTime.now())
                        .value(value)
                        .build()
        );

        CompletableFuture.runAsync(integrationsService::notification);

        return transferMapper.toDto(entity);
    }

    private void validTransfer(WalletEntity walletSender, BigDecimal value) {
        if(walletSender.getUser().isMerchant()){
            throw new BusinessException("Transfer no allowed to merchant user.");
        }

        if(!walletSender.hasBalance(value)){
            throw new BusinessException("Insufficient balance");
        }

        if(!integrationsService.authorize()){
            throw new BusinessException("Transfer not authorize.");
        }
    }
}
