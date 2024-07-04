package br.com.samueljunnior.module.user.entity;

import br.com.samueljunnior.module.user.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    public static final String ID_USER = "id_usuario";

    @Id
    @Column(name = ID_USER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "des_nome_completo")
    private String fullName;

    @Column(name = "des_documento")
    private String document;

    @Column(name = "des_email")
    private String email;

    @Column(name = "des_senha")
    private String password;

    @ManyToOne
    @JoinColumn(name = UserTypeEntity.ID_USER_TYPE)
    private UserTypeEntity type;

    public boolean isMerchant() {
        return this.type.getId().intValue() == UserTypeEnum.MERCHANT.getId();
    }
}
