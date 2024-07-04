package br.com.samueljunnior.module.wallet.service;

import br.com.samueljunnior.core.exceptions.BusinessException;
import br.com.samueljunnior.core.exceptions.NotFoundException;
import br.com.samueljunnior.module.wallet.dto.DepositValueDTO;
import br.com.samueljunnior.module.wallet.dto.WalletDTO;
import br.com.samueljunnior.module.wallet.entity.WalletEntity;
import br.com.samueljunnior.module.wallet.mapper.WalletMaper;
import br.com.samueljunnior.module.wallet.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMaper walletMaper;

    public WalletEntity getWallet(Long idWallet){
        return walletRepository.findById(idWallet)
                .orElseThrow(NotFoundException::new);
    }

    public WalletEntity getWalletByUser(Long idUser){
        return walletRepository.findByUserId(idUser)
                .orElseThrow(NotFoundException::new);
    }

    public WalletDTO getWalletDataByUser(Long idUser){
        return walletMaper.toDto(this.getWalletByUser(idUser));
    }

    public WalletDTO makeDeposit(DepositValueDTO dto){
        return this.creditValue(dto.idWallet(), new BigDecimal(dto.value()));
    }

    public WalletDTO creditValue(Long idWallet, BigDecimal value){
        final var wallet = this.getWallet(idWallet);

        if(value.compareTo(BigDecimal.ZERO) < 0){
            throw new BusinessException("Negative values not allowed.");
        }

        wallet.setBalance(wallet.getBalance().add(value));
        return walletMaper.toDto(walletRepository.save(wallet));
    }

    public WalletDTO debitValue(Long idWallet, BigDecimal value){
        final var wallet = this.getWallet(idWallet);

        if(value.compareTo(wallet.getBalance()) > 0){
            throw new BusinessException("Debit amount cannot exceed account balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(value));
        return walletMaper.toDto(walletRepository.save(wallet));
    }

}
