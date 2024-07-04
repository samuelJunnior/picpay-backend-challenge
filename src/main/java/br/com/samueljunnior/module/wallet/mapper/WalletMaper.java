package br.com.samueljunnior.module.wallet.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.module.wallet.dto.WalletDTO;
import br.com.samueljunnior.module.wallet.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletMaper extends BaseMapper<WalletEntity, WalletDTO> {

    @Mapping(target = "idUser", source = "entity.user.id")
    WalletDTO toDto(WalletEntity entity);
}
