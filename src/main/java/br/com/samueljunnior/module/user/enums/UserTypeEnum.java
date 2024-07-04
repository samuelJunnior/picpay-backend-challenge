package br.com.samueljunnior.module.user.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserTypeEnum {
    COMMONS(1, "COMUNS"),
    MERCHANT(2, "LOJISTAS");

    private final Integer id;
    private final String description;

    UserTypeEnum(Integer id, String description){
        this.id = id;
        this.description = description;
    }

    public static UserTypeEnum findById(Integer id){
        return Arrays.stream(UserTypeEnum.values())
                .filter(us -> us.getId().equals(id))
                .findAny()
                .orElseThrow();
    }
}
