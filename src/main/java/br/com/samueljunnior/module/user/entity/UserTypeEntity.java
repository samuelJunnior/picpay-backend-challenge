package br.com.samueljunnior.module.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_tipo_usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeEntity {

    public static final String ID_USER_TYPE = "id_tipo_usuario";

    @Id
    @Column(name = ID_USER_TYPE)
    private Long id;

    @Column(name = "des_descricao")
    private String description;
}
