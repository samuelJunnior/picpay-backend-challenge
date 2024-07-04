package br.com.samueljunnior.module.user.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.module.user.dto.UserFilterDTO;
import br.com.samueljunnior.module.user.entity.UserEntity;
import br.com.samueljunnior.module.user.entity.UserTypeEntity;
import br.com.samueljunnior.module.user.enums.UserTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserFilterMaper extends BaseMapper<UserEntity, UserFilterDTO> {

    @Mapping(target = "type", source = "dto.type", qualifiedByName = "enumTypeToEntity")
    UserEntity toEntity(UserFilterDTO dto);

    @Mapping(target = "type", source = "entity.type", qualifiedByName = "entityTypeToEnum")
    UserFilterDTO toDto(UserEntity entity);

    @Named("enumTypeToEntity")
    default UserTypeEntity enumTypeToEntity(UserTypeEnum typeEnum){
        if(Objects.isNull(typeEnum)){
            return null;
        }

        return UserTypeEntity.builder().id(typeEnum.getId().longValue()).description(typeEnum.getDescription()).build();
    }

    @Named("entityTypeToEnum")
    default UserTypeEnum entityTypeToEnum(UserTypeEntity typeEntity){
        return UserTypeEnum.findById(typeEntity.getId().intValue());
    }
}
