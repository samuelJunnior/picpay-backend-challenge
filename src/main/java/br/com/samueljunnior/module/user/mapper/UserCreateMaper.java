package br.com.samueljunnior.module.user.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.module.user.dto.UserCreateDTO;
import br.com.samueljunnior.module.user.entity.UserEntity;
import br.com.samueljunnior.module.user.entity.UserTypeEntity;
import br.com.samueljunnior.module.user.enums.UserTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreateMaper extends BaseMapper<UserEntity, UserCreateDTO> {

    @Mapping(target = "type", source = "dto.type", qualifiedByName = "enumTypeToEntity")
    UserEntity toEntity(UserCreateDTO dto);

    @Mapping(target = "type", source = "entity.type", qualifiedByName = "entityTypeToEnum")
    UserCreateDTO toDto(UserEntity entity);

    @Named("enumTypeToEntity")
    default UserTypeEntity enumTypeToEntity(UserTypeEnum typeEnum){
        return UserTypeEntity.builder().id(typeEnum.getId().longValue()).description(typeEnum.getDescription()).build();
    }

    @Named("entityTypeToEnum")
    default UserTypeEnum entityTypeToEnum(UserTypeEntity typeEntity){
        return UserTypeEnum.findById(typeEntity.getId().intValue());
    }
}
