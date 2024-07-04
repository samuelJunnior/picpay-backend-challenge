package br.com.samueljunnior.module.transfer.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.module.transfer.dto.TransferDTO;
import br.com.samueljunnior.module.transfer.entity.TransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMapper extends BaseMapper<TransferEntity, TransferDTO> {

    @Mapping(target = "idSender", source = "entity.sender.id")
    @Mapping(target = "idReceiver", source = "entity.receiver.id")
    TransferDTO toDto(TransferEntity entity);
}
